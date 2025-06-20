package org.example.shortlink.admin.dto.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.example.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;

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
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;


}
