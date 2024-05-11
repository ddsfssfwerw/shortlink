package org.example.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.shortlink.project.dao.entity.ShortLinkDO;

/**
 * @author LLY
 * @className ShortLinkCreateReqDTO
 * @date 2024/5/9
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    private String gid;


}
