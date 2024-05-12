package org.example.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.example.shortlink.admin.dto.resq.ShortLinkGroupResqDTO;

import java.util.List;

/**
 * @author LLY
 * @InterfaceName GroupServise
 * @date 2024/5/9
 */
public interface GroupServise extends IService<GroupDO> {

    /**
     * 新增分组
     * @param name
     */
    void saveGroup(String name);

    /**
     * 新增分组
     * @param username
     * @param name
     */
    void saveGroup(String username,String name);
    /**
     * 查询分组
     */
    List<ShortLinkGroupResqDTO> listGroup();

    /**
     * 修改分组
     * @param updateReqDTO
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO updateReqDTO);

    /**
     * 删除分组
     * @param gid
     */
    void deleteGroup(String gid);

    /**
     * 排序分组
     * @param shortLinkGroupSortReqDTO
     */
    void sortGroup(List<ShortLinkGroupSortReqDTO> shortLinkGroupSortReqDTO);
}
