package org.example.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shortlink.project.dao.entity.ShortLinkDO;
import org.example.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.example.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkPageResqDTO;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午7:01
 * @className RecycleBinService
 * @copyright LLY
 */
public interface RecycleBinService extends IService<ShortLinkDO> {
    /**
     * 新增
     * @param requestParam
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询
     * @param shortLinkPageReqDTO
     * @return
     */
    IPage<ShortLinkPageResqDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO shortLinkPageReqDTO);

}
