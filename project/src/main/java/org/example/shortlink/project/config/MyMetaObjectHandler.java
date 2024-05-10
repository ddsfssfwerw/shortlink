package org.example.shortlink.project.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author LLY
 * @className MyMetaObjectHandler
 * @date 2024/5/9
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        strictInsertFill(metaObject, "createTime", Date::new,  Date.class); // 起始版本 3.3.0(推荐使用)
        // 或者
        strictInsertFill(metaObject, "updateTime",  Date::new,  Date.class); // 起始版本 3.3.3(推荐)
        // 或者
        strictInsertFill(metaObject, "delFlag", () -> 0,Integer.class); // 也可以使用(3.3.0 该方法有bug)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        strictInsertFill(metaObject, "updateTime",  Date::new,  Date.class);
    }
}
