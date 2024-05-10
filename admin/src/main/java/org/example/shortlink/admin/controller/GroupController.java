package org.example.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.admin.common.convention.result.Result;
import org.example.shortlink.admin.common.convention.result.Results;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.example.shortlink.admin.dto.resq.ShortLinkGroupResqDTO;
import org.example.shortlink.admin.service.GroupServise;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/api/short-link/admin/v1/group")
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
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupResqDTO>> listGroup() {
        List<ShortLinkGroupResqDTO> list =  groupServise.listGroup();
        log.info("查询分组结果： {}",list);
        return Results.success(list);
    }

    /**
     * 修改分组
     * @param updateReqDTO
     * @return
     */
    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO updateReqDTO) {
        log.info("修改分组： {}", updateReqDTO.getName());
        groupServise.updateGroup(updateReqDTO);
        return Results.success();
    }


    /**
     * 删除分组
     * @param gid
     * @return
     */
    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> deleteGroup(@RequestParam String gid ) {
        log.info("删除分组： {}", gid);
        groupServise.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 排序分组
     * @param shortLinkGroupSortReqDTO
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> shortLinkGroupSortReqDTO) {
        log.info("排序分组：");
        groupServise.sortGroup(shortLinkGroupSortReqDTO);
        return Results.success();
    }
}
