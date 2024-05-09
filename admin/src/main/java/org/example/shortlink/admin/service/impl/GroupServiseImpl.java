package org.example.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dao.mapper.GroupMapper;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.example.shortlink.admin.service.GroupServise;
import org.example.shortlink.admin.toolkit.RandomStringUtil;
import org.springframework.stereotype.Service;

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
        GroupDO build = GroupDO.builder().gid(gid).name(saveReqDTO.getName()).build();
        baseMapper.insert(build);

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
