package org.example.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author LLY
 * @className ShortLinkCreateReqDTO
 * @date 2024/5/9
 */
@Data
public class ShortLinkUpdateReqDTO {

    /**
     * 域名
     */
    private String domain;

    /**
     * 完整短链接
     */
    private String fullShortUrl;


    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 分组标识
     */
    private String gid;


    /**
     * 有效期类型 0：永久  1自定义
     */
    private int validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述
     */
    private String describe;

    /**
     * 网站图标
     */
    private String favicon;
}
