package org.example.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.biz.user.UserContext;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dao.mapper.GroupMapper;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.example.shortlink.admin.dto.resq.ShortLinkGroupResqDTO;
import org.example.shortlink.admin.remote.ShortLinkRemoteService;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkGroupCountQueryResqDTO;
import org.example.shortlink.admin.service.GroupServise;
import org.example.shortlink.admin.toolkit.RandomStringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author LLY
 * @className GroupServiseImpl
 * @date 2024/5/9
 */
@Service
@Slf4j
@RequiredArgsConstructor//注入bean
public class GroupServiseImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupServise {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 新增分组
     * @param saveReqDTO
     */
    @Override
    public void saveGroup(ShortLinkGroupSaveReqDTO saveReqDTO) {
        String gid;
        do{
            gid = RandomStringUtil.generateRandomString();
            if (hasGroup(gid)){
                break;
            }
        }while (true);
        GroupDO build = GroupDO.builder()
                .gid(gid)
                .username(UserContext.getUsername())
                .sortOrder(0)
                .name(saveReqDTO.getName())
                .build();
        baseMapper.insert(build);

    }

    /**
     * 查询分组
     *
     * @return
     */
    @Override
    public List<ShortLinkGroupResqDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder,GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(eq);
        Result<List<ShortLinkGroupCountQueryResqDTO>> listResult = shortLinkRemoteService
                .listGroupShortLinkCount(groupDOList.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupResqDTO> list = BeanUtil.copyToList(groupDOList, ShortLinkGroupResqDTO.class);
        list.forEach(each -> {
            Optional<ShortLinkGroupCountQueryResqDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        });
        return list;
    }

    /**
     * 修改分组
     * @param updateReqDTO
     */
    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO updateReqDTO) {
        LambdaUpdateWrapper<GroupDO> eq = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getGid, updateReqDTO.getGid());
        GroupDO groupDO = new GroupDO();
        groupDO.setName(updateReqDTO.getName());
        baseMapper.update(groupDO, eq);

    }

    /**
     * 删除分组
     * @param gid
     */
    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> eq = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getGid, gid);
        GroupDO groupDO = new GroupDO();
        //软删除
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, eq);

    }

    /**
     * 排序
     * @param shortLinkGroupSortReqDTO
     */
    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> shortLinkGroupSortReqDTO) {
        shortLinkGroupSortReqDTO.forEach(each ->{
            GroupDO build = GroupDO.builder()
                    .sortOrder(each.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> eq = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(build, eq);
        });

    }

    private boolean hasGroup(String gid){
        String username = UserContext.getUsername();
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, username);
        GroupDO groupDO = baseMapper.selectOne(eq);
        return groupDO == null;
    }
}
