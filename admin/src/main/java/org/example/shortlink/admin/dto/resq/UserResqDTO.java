package org.example.shortlink.admin.dto.resq;

import lombok.Data;

/**用户返回参数实体
 * @author LLY
 * @className UserResqDTO
 * @date 2024/5/7
 */
@Data
public class UserResqDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

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
