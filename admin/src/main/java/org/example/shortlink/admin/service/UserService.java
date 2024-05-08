package org.example.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shortlink.admin.dao.entity.UserDO;
import org.example.shortlink.admin.dto.resq.UserResqDTO;

/**
 * @author LLY
 * @InterfaceName UserService
 * @date 2024/5/7
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserResqDTO getUserByUsername(String username);

}
