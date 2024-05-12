package org.example.shortlink.project.dao.entity;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/10 下午3:23
 * @className ShortLinkDO
 * @copyright LLY
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.example.shortlink.project.common.database.BaseDO;

import java.util.Date;


@TableName("t_link_goto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortLinkGotoDO {

    /**
     * id
     */
    private Long id;




    /**
     * 分组标识
     */
    private String gid;


    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
