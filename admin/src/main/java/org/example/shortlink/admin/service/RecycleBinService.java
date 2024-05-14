package org.example.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkPageResqDTO;

/**
 * @author LLY
 * @InterfaceName UserService
 * @date 2024/5/7
 */
public interface RecycleBinService {


    /**
     *
     * @param reqDTO
     * @return
     */
    Result<IPage<ShortLinkPageResqDTO>> pageRecyclebinShortLink(ShortLinkRecycleBinPageReqDTO reqDTO);
}
