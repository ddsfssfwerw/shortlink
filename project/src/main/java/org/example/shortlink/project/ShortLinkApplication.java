package org.example.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/10 下午3:31
 * @className ShortLinkApplication
 * @copyright LLY
 */
@SpringBootApplication
@MapperScan("org.example.shortlink.project.dao.mapper")
public class ShortLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);

    }
}
