package org.example.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.project.common.constant.RedisKeyConstant;
import org.example.shortlink.project.dao.entity.ShortLinkDO;
import org.example.shortlink.project.dao.mapper.ShortLinkGotoMapper;
import org.example.shortlink.project.dao.mapper.ShortLinkMapper;
import org.example.shortlink.project.dto.req.*;
import org.example.shortlink.project.dto.resq.ShortLinkPageResqDTO;
import org.example.shortlink.project.service.RecycleBinService;
import org.example.shortlink.project.toolkit.LinkUtil;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午7:03
 * @className RecycleBinServiceImpl
 * @copyright LLY
 */
@Service
@Slf4j
@RequiredArgsConstructor//注入bean
public class RecycleBinServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements RecycleBinService {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * @param requestParam
     */
    @Override
    public void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {

        LambdaUpdateWrapper<ShortLinkDO> eq = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getDelFlag, 0);
        ShortLinkDO build = ShortLinkDO.builder().enableStatus(1).build();
        baseMapper.update(build, eq);
        stringRedisTemplate.delete(String.format(RedisKeyConstant.GOTO_SHORT_LINK_KEY, requestParam.getFullShortUrl()));
    }

    /**
     * 分页查询
     *
     * @param shortLinkPageReqDTO
     * @return
     */
    @Override
    public IPage<ShortLinkPageResqDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO shortLinkPageReqDTO) {
        LambdaQueryWrapper<ShortLinkDO> eq = Wrappers.lambdaQuery(ShortLinkDO.class)
                .in(ShortLinkDO::getGid,shortLinkPageReqDTO.getGids())
                .eq(ShortLinkDO::getEnableStatus, 1)
                .eq(ShortLinkDO::getDelFlag, 0)
                .orderByAsc(ShortLinkDO::getUpdateTime);
        IPage<ShortLinkDO> p = baseMapper.selectPage(shortLinkPageReqDTO, eq);
        return p.convert(each -> {
            ShortLinkPageResqDTO result = BeanUtil.toBean(each, ShortLinkPageResqDTO.class);
            result.setDomain("http://" + result.getDomain());
            return result;
        });
    }

    /**
     * 移出回收站
     * @param requestParam
     * @return
     */
    @Override
    public void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam) {
        LambdaUpdateWrapper<ShortLinkDO> eq = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 1)
                .eq(ShortLinkDO::getDelFlag, 0);
        ShortLinkDO build = ShortLinkDO.builder()
                .enableStatus(0)
                .build();
        baseMapper.update(build, eq);
        stringRedisTemplate.delete(String.format(RedisKeyConstant.GOTO_IS_NULL_SHORT_LINK_KEY, requestParam.getFullShortUrl()));
    }

    @Override
    public void removreRecycleBin(RecycleBinRemoveReqDTO requestParam) {
        LambdaUpdateWrapper<ShortLinkDO> eq = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 1)
                .eq(ShortLinkDO::getDelFlag, 0);
        ShortLinkDO build = ShortLinkDO.builder()
                .delFlag(1)
                .build();
        baseMapper.update(build, eq);
        stringRedisTemplate.delete(String.format(RedisKeyConstant.GOTO_IS_NULL_SHORT_LINK_KEY, requestParam.getFullShortUrl()));

    }
}
