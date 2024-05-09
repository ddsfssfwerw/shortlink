package org.example.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shortlink.admin.dao.entity.GroupDO;
import org.example.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
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
     * @param saveReqDTO
     */
    void saveGroup(ShortLinkGroupSaveReqDTO saveReqDTO);

    /**
     * 查询分组
     */
    List<ShortLinkGroupResqDTO> listGroup();
}
