package org.example.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @author LLY
 * @className ShortLinkGroupSaveReqDTO
 * @date 2024/5/9
 */
@Data
public class ShortLinkGroupUpdateReqDTO {


    /**
     * 分组id
     */
    private String gid;
    /**
     * 分组名
     */
    private String name;
}
