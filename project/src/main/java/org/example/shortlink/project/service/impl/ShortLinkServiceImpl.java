package org.example.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.project.common.convention.exception.ServiceException;
import org.example.shortlink.project.dao.entity.ShortLinkDO;
import org.example.shortlink.project.dao.mapper.ShortLinkMapper;
import org.example.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.project.service.ShortLinkService;
import org.example.shortlink.project.toolkit.HashUtil;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Service;

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
        } catch (Exception e) {
            //TODO
            log.warn("短链接：{} 重复入库",fullShortUrl);
            throw new ServiceException("短链接生成重复");
        }
        shortUriCreaterCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateResqDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkCreateReqDTO.getOriginUrl())
                .gid(shortLinkCreateReqDTO.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        int count = 0;
        String shortUri;
        while (true){
            if (count > 10){
                throw new ServiceException("短链接频繁生成，请稍后在试");
            }
            String originUrl = shortLinkCreateReqDTO.getOriginUrl();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreaterCachePenetrationBloomFilter.contains(shortLinkCreateReqDTO.getDomain()+"/"+shortUri)){
                break;
            }
            count++;
        }
        return shortUri;
    }
}
