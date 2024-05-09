package org.example.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dao.mapper.GroupMapper;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.example.shortlink.admin.dto.resq.ShortLinkGroupResqDTO;
import org.example.shortlink.admin.service.GroupServise;
import org.example.shortlink.admin.toolkit.RandomStringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LLY
 * @className GroupServiseImpl
 * @date 2024/5/9
 */
@Service
@Slf4j
public class GroupServiseImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupServise {

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
        //TODO 获取用户名
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, 1)
                .orderByAsc(GroupDO::getSortOrder,GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(eq);
        return BeanUtil.copyToList(groupDOList, ShortLinkGroupResqDTO.class);
    }

    private boolean hasGroup(String gid){
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                //TODO 设置用户名
                .eq(GroupDO::getUsername, null);
        GroupDO groupDO = baseMapper.selectOne(eq);
        return groupDO == null;
    }
}
