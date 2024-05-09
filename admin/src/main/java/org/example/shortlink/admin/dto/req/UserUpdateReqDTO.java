package org.example.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @author LLY
 * @className UserUpdateReqDTO
 * @date 2024/5/9
 */
@Data
public class UserUpdateReqDTO {
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
