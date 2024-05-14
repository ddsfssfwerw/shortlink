package org.example.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.shortlink.admin.common.database.BaseDO;

/**
 * @author LLY
 * @className GroupDO
 * @date 2024/5/9
 */
@Data
@TableName("t_group")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDO extends BaseDO {


    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 删除标识
     */
    private Integer delFlag;


}
