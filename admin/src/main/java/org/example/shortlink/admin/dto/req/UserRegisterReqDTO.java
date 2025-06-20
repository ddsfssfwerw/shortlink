package org.example.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @author LLY
 * @className UserRegisterReqDTO
 * @date 2024/5/8
 */
@Data
public class UserRegisterReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
