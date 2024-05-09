package org.example.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @author LLY
 * @className UserLoginReqDTO
 * @date 2024/5/9
 */
@Data
public class UserLoginReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
