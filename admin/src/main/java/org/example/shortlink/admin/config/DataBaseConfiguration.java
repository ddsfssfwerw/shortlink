package org.example.shortlink.admin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/11 上午9:08
 * @className DataBaseConfiguration
 * @copyright LLY
 */
@Configuration
public class DataBaseConfiguration {

    /**
     * 分页插件
     *
     * @return
     */
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
