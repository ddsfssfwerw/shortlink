package org.example.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.example.shortlink.project.dao.entity.LinkAccessStatsDO;
import org.example.shortlink.project.dao.entity.ShortLinkDO;

/**
 * @author LLY
 * @className UserMapper
 * @date 2024/5/7
 */
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {

    @Insert("")
    void shortLinkAccessStats(@Param("linkAccessStats") LinkAccessStatsDO linkAccessStatsDO);

}
