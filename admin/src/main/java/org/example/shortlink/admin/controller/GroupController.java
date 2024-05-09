package org.example.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.common.convention.result.Results;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.example.shortlink.admin.dto.resq.ShortLinkGroupResqDTO;
import org.example.shortlink.admin.service.GroupServise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LLY
 * @className GroupController
 * @date 2024/5/9
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class GroupController {
    private final GroupServise groupServise;

    /**
     * 新建分组
     * @param shortLinkGroupSaveReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO) {
        log.info("新增分组： {}", shortLinkGroupSaveReqDTO.getName());
        groupServise.saveGroup(shortLinkGroupSaveReqDTO);
        return Results.success();
    }

    /**
     * 查询分组
     * @param
     * @return
     */
    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupResqDTO>> listGroup() {
        List<ShortLinkGroupResqDTO> list =  groupServise.listGroup();
        log.info("查询分组结果： {}",list);
        return Results.success(list);
    }
}
