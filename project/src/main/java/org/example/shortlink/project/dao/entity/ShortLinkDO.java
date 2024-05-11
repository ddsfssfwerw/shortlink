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

/**
 * @description t_link
 * @author zhengkai.blog.csdn.net
 * @date 2024-05-10
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_link")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortLinkDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 点击量
     */
    private Integer clickNum;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 启用标识0：启用
     */
    private int enableStatus;

    /**
     * 创建类型
     */
    private int createdType;

    /**
     * 有效期类型
     */
    private int validDateType;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;

    /**
     * 网站图标
     */
    private String favicon;

}
