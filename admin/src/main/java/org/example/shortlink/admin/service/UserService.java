package org.example.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shortlink.admin.dao.entity.UserDO;
import org.example.shortlink.admin.dto.req.UserLoginReqDTO;
import org.example.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.example.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.example.shortlink.admin.dto.resq.UserLoginResqDTO;
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

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    Boolean hasUserName(String username);

    /**
     * 注册
     * @param userRegisterReqDTO
     */
    void register(UserRegisterReqDTO userRegisterReqDTO);

    /**
     * 修改用户信息
     * @param updateReqDTO
     */
    void update(UserUpdateReqDTO updateReqDTO);

    /**
     * 用户登录
     * @param userLoginReqDTO
     * @return
     */
    UserLoginResqDTO login(UserLoginReqDTO userLoginReqDTO);

    /**
     * 检查用户是否登录
     * @param token
     */
    Boolean checkLogin(String name,String token);
}
