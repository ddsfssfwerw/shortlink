package org.example.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shortlink.project.dao.entity.ShortLinkDO;
import org.example.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkPageResqDTO;

/**
 * @author LLY
 * @InterfaceName ShortLinkService
 * @date 2024/5/10
 */
public interface ShortLinkService extends IService<ShortLinkDO> {


    /**
     * 创建
     * @param shortLinkCreateReqDTO
     * @return
     */
    ShortLinkCreateResqDTO createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO);

    /**
     * 分页查询
     * @param shortLinkPageReqDTO
     * @return
     */
    IPage<ShortLinkPageResqDTO> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO);
}
