package org.example.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dao.mapper.GroupMapper;
import org.example.shortlink.admin.service.GroupServise;
import org.springframework.stereotype.Service;

/**
 * @author LLY
 * @className GroupServiseImpl
 * @date 2024/5/9
 */
@Service
@Slf4j
public class GroupServiseImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupServise {
}
