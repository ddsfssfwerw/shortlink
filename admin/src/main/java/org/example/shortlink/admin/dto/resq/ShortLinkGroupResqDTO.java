package org.example.shortlink.admin.dto.resq;

import lombok.Data;

/**
 * @author LLY
 * @className ShortLinkGroupSaveReqDTO
 * @date 2024/5/9
 */
@Data
public class ShortLinkGroupResqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 分组下短链接数量
     */
    private Integer shortLinkCount;
}
