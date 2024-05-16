package org.example.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.biz.user.UserContext;
import org.example.shortlink.admin.common.convention.exception.ServiceException;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dao.mapper.GroupMapper;
import org.example.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.example.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkPageResqDTO;
import org.example.shortlink.admin.service.RecycleBinService;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class RecycleBinServiceImpl implements RecycleBinService {
    private final GroupMapper groupMapper;

    ShortLinkActualRemoteService shortLinkActualRemoteService = new ShortLinkActualRemoteService() {
    };

    @Override
    public Result<IPage<ShortLinkPageResqDTO>> pageRecyclebinShortLink(ShortLinkRecycleBinPageReqDTO reqDTO) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)){
            throw new ServiceException("用户无分组信息");
        }
        reqDTO.setGids(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkActualRemoteService.pageRecyclebinShortLink(reqDTO);
    }
}
