package org.example.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import org.example.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import org.example.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.common.convention.result.Results;
import org.example.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import org.example.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.example.shortlink.admin.remote.dto.resq.ShortLinkPageResqDTO;
import org.example.shortlink.admin.service.RecycleBinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午6:59
 * @className RecycleBinController
 * @copyright LLY
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class RecycleBinController {
    private final RecycleBinService recycleBinService;
    ShortLinkActualRemoteService shortLinkActualRemoteService = new ShortLinkActualRemoteService() {
    };

    /**
     * 新增回收站
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam){
        shortLinkActualRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * 分页查询回收站短链接
     * @param shortLinkPageReqDTO
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageResqDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO shortLinkPageReqDTO){
        //log.info("分页查询回收站短链接，gid：{}",shortLinkPageReqDTO.getGid());
        return recycleBinService.pageRecyclebinShortLink(shortLinkPageReqDTO);

    }

    /**
     * 移出回收站
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam){
        log.info("移除回收站：{}", requestParam.getFullShortUrl());
        shortLinkActualRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * 彻底删除短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removreRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam){
        log.info("彻底删除短链接：{}", requestParam.getFullShortUrl());
        shortLinkActualRemoteService.removreRecycleBin(requestParam);
        return Results.success();
    }
}
