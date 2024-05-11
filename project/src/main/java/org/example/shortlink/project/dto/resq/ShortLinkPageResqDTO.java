package org.example.shortlink.project.dto.resq;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author LLY
 * @className ShortLinkCreateReqDTO
 * @date 2024/5/9
 */
@Data
public class ShortLinkPageResqDTO extends Page {

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
