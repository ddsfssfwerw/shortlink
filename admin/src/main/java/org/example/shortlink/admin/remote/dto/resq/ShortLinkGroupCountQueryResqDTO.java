package org.example.shortlink.admin.remote.dto.resq;

import lombok.Data;

/**
 * @author LLY
 * @className ShortLinkGroupSaveReqDTO
 * @date 2024/5/9
 */
@Data
public class ShortLinkGroupCountQueryResqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组下短链接数量
     */
    private Integer shortLinkCount;
}
