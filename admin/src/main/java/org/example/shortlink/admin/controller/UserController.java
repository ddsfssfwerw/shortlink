package org.example.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.common.convention.result.Results;
import org.example.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.example.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.example.shortlink.admin.dto.resq.UserActualResqDTO;
import org.example.shortlink.admin.dto.resq.UserResqDTO;
import org.example.shortlink.admin.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author LLY
 * @className UserController
 * @date 2024/5/7
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;


    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserResqDTO> getUserByUsername(@PathVariable("username") String username) {
        log.info("getUserByUsername {}", username);
        UserResqDTO userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            return new Result<UserResqDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        }
        return Results.success(userByUsername);
    }

    /**
     * 根据用户名查找用户无脱敏信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/v1/actual/user/{username}")
    public Result<UserActualResqDTO> getActualUserByUsername(@PathVariable("username") String username) {
        log.info("getUserByUsername(无脱敏信息) {}", username);
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualResqDTO.class));
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        log.info("检查是否存在用户名：{}", username);
        //log.info("结果：{}", userService.hasUserName(username));
        return Results.success(userService.hasUserName(username));
    }

    /**
     * 注册
     * @param userRegisterReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO){
        log.info("注册用户： {}", userRegisterReqDTO.getUsername());
        userService.register(userRegisterReqDTO);
        return Results.success();

    }
}
