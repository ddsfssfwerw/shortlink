package org.example.shortlink.admin.dto.resq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LLY
 * @className UserLoginReqDTO
 * @date 2024/5/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResqDTO {

    /**
     * 用户token
     */
    private String token;
}
