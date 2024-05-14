package org.example.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.remote.ShortLinkRemoteService;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.common.convention.result.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午4:21
 * @className UrlTitleController
 * @copyright LLY
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlTitleController {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };


    /**
     * 获取url的标题.
     * @param url
     * @return
     */
    @GetMapping("api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        log.info("获取url：{}的标题", url);
        return shortLinkRemoteService.getTitleByUrl(url);

    }

}
