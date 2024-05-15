package org.example.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午6:57
 * @className RecycleBinSaveReqDTO
 * @copyright LLY
 */
@Data
public class RecycleBinRemoveReqDTO {

 /**
  * 分组标识
  */
 private String gid;

 /**
  *
  */
 private String fullShortUrl;

}
