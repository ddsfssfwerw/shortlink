package org.example.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.common.convention.result.Results;
import org.example.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.example.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkPageResqDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/11 上午10:14
 * @className ShortLinkController
 * @copyright LLY
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ShortLinkController {
    ShortLinkActualRemoteService shortLinkActualRemoteService = new ShortLinkActualRemoteService() {
    };


    /**
     *创建
     * @param shortLinkCreateReqDTO
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateResqDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO){
        //ShortLinkCreateResqDTO shortLinkCreateResqDTO = ShortLinkActualRemoteService.createShortLink(shortLinkCreateReqDTO);
        Result<ShortLinkCreateResqDTO> shortLink = ShortLinkActualRemoteService.createShortLink(shortLinkCreateReqDTO);
        log.info("创建短链接： {}", shortLink.getData().getFullShortUrl());
        return shortLink;
    }

    /**
     * 修改短链接
     * @param shortLinkUpdateReqDTO
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO shortLinkUpdateReqDTO){
        log.info("修改短链接,gid:{},fullShortUrl:{}",shortLinkUpdateReqDTO.getGid(),shortLinkUpdateReqDTO.getFullShortUrl());
        shortLinkActualRemoteService.updateShortLink(shortLinkUpdateReqDTO);
        return Results.success();

    }


    /**
     * 分页查询短链接
     * @param shortLinkPageReqDTO
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageResqDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO){
        log.info("分页查询短链接：");
        return shortLinkActualRemoteService.pageShortLink(shortLinkPageReqDTO);

    }


}
