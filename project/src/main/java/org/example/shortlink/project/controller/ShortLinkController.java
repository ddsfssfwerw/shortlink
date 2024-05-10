package org.example.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.project.common.convention.result.Result;
import org.example.shortlink.project.common.convention.result.Results;
import org.example.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.example.shortlink.project.dto.resq.ShortLinkCreateResqDTO;
import org.example.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
