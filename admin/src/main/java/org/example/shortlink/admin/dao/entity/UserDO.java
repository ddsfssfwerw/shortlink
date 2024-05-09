package org.example.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.shortlink.admin.common.database.BaseDO;

/**
 * @author LLY
 * @className UserDO
 * @date 2024/5/7
 */
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

        private static final long serialVersionUID = 1L;

        /**
         * id
         */
        private Long id;

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

        /**
         * 注销时间戳
         */
        private Long deletionTime;


}
