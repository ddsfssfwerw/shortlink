package org.example.shortlink.project.controller;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.project.common.convention.result.Result;
import org.example.shortlink.project.common.convention.result.Results;
import org.example.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkGroupCountQueryResqDTO;
import org.example.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkPageResqDTO;
import org.example.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

/**
 * @author LLY
 * @className UserController
 * @date 2024/5/7
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     *创建
     * @param shortLinkCreateReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateResqDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO){
        ShortLinkCreateResqDTO shortLinkCreateResqDTO = shortLinkService.createShortLink(shortLinkCreateReqDTO);
        log.info("创建短链接： {}", shortLinkCreateResqDTO.getFullShortUrl());
        return Results.success(shortLinkCreateResqDTO);
    }

    /**
     * 分页查询
     * @param shortLinkPageReqDTO
     * @return
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageResqDTO>> pageShortLink( ShortLinkPageReqDTO shortLinkPageReqDTO){
        log.info("分页查询短链接，gid:{}", shortLinkPageReqDTO.getGid());
        return Results.success(shortLinkService.pageShortLink(shortLinkPageReqDTO));
    }

    /**
     * 查询短链接组内数量
     * @param gids
     * @return
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryResqDTO>> listGroupShortLinkCount(@RequestParam("gids") List<String> gids){
        log.info("查询短链接组内数量,gid：{}",gids);
        return Results.success(shortLinkService.listGroupShortLinkCount(gids));

    }
    /**
     * 修改短链接
     * @param shortLinkUpdateReqDTO
     * @return
     *     "createdType": 37,
     *     "validDateType": 44,
     *     "validDate": "",
     *     "describe": "Excepteur"
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO shortLinkUpdateReqDTO){
        log.info("修改短链接：{}",shortLinkUpdateReqDTO.getGid());
        shortLinkService.updateShortLink(shortLinkUpdateReqDTO);
        return Results.success();

    }

    /**
     * 跳转
     * @param shortUri
     * @param request
     * @param response
     */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@RequestParam("short-uri") String shortUri, ServletRequest request, ServletResponse response){
        shortLinkService.restoreUrl(shortUri,request,response);

    }
}
