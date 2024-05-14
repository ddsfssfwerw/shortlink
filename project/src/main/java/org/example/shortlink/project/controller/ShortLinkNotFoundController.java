package org.example.shortlink.project.controller;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午3:03
 * @className ShortLinkNotFoundController
 * @copyright LLY
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 短链接不存在跳转控制器
 */
@Controller
public class ShortLinkNotFoundController {

    /**
     * 短链接不存在跳转页面
     */
    @RequestMapping("/page/notfound")
    public String notfound() {
        return "notfound";
    }
}