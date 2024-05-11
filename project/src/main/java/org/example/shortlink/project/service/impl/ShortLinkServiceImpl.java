package org.example.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.project.common.convention.exception.ServiceException;
import org.example.shortlink.project.dao.entity.ShortLinkDO;
import org.example.shortlink.project.dao.mapper.ShortLinkMapper;
import org.example.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.project.dto.req.ShortLinkGroupCountQueryResqDTO;
import org.example.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkPageResqDTO;
import org.example.shortlink.project.service.ShortLinkService;
import org.example.shortlink.project.toolkit.HashUtil;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户接口实现层
 *
 * @author LLY
 * @className UserServiceImpl
 * @date 2024/5/7
 */
@Service
@Slf4j
@RequiredArgsConstructor//注入bean
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreaterCachePenetrationBloomFilter;
    /**
     * 创建
     * @param shortLinkCreateReqDTO
     * @return
     */
    @Override
    public ShortLinkCreateResqDTO createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        String generateSuffix = generateSuffix(shortLinkCreateReqDTO);
        String fullShortUrl = shortLinkCreateReqDTO.getDomain()+"/"+generateSuffix;
        ShortLinkDO shortLinkDO = BeanUtil.toBean(shortLinkCreateReqDTO, ShortLinkDO.class);
        shortLinkDO.setShortUri(generateSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(fullShortUrl);
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException ex) {
            LambdaQueryWrapper<ShortLinkDO> eq = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(t -> shortLinkDO.getFullShortUrl(), fullShortUrl);
            ShortLinkDO shortLinkDO1 = baseMapper.selectOne(eq);
            if (shortLinkDO1 != null){
                log.warn("短链接：{} 重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        shortUriCreaterCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateResqDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkCreateReqDTO.getOriginUrl())
                .gid(shortLinkCreateReqDTO.getGid())
                .build();
    }

    /**
     * 分页查询
     * @param shortLinkPageReqDTO
     * @return
     */
    @Override
    public IPage<ShortLinkPageResqDTO> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO) {
        LambdaQueryWrapper<ShortLinkDO> eq = Wrappers.lambdaQuery(ShortLinkDO.class).eq(ShortLinkDO::getGid, shortLinkPageReqDTO.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .eq(ShortLinkDO::getDelFlag, 0)
                .orderByAsc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> p = baseMapper.selectPage(shortLinkPageReqDTO, eq);
        return p.convert(each -> BeanUtil.toBean(each, ShortLinkPageResqDTO.class));
    }

    /**
     * 查询短链接组内数量
     * @param gids
     * @return
     */
    @Override
    public List<ShortLinkGroupCountQueryResqDTO> listGroupShortLinkCount(List<String> gids) {
        QueryWrapper<ShortLinkDO> shortLinkDOQueryWrapper = Wrappers.query(new ShortLinkDO())
                .select("gid as gid, count(*) as shortLinkCount")
                .in("gid", gids)
                .eq("enable_status", 0)
                .groupBy("gid");
        List<Map<String, Object>> maps = baseMapper.selectMaps(shortLinkDOQueryWrapper);
        return BeanUtil.copyToList(maps, ShortLinkGroupCountQueryResqDTO.class);
    }

    private String generateSuffix(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        int count = 0;
        String shortUri;
        while (true){
            if (count > 10){
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = shortLinkCreateReqDTO.getOriginUrl();
            originUrl += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreaterCachePenetrationBloomFilter.contains(shortLinkCreateReqDTO.getDomain()+"/"+shortUri)){
                break;
            }
            count++;
        }
        return shortUri;
    }
}
