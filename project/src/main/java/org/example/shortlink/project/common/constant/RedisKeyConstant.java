package org.example.shortlink.project.common.constant;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午12:14
 * @className RedisKeyConstant
 * @copyright LLY
 */
public class RedisKeyConstant {

    /**
     * 短链接跳转前缀Key
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * 短链接空值跳转前缀Key
     */
    public static final String GOTO_IS_NULL_SHORT_LINK_KEY = "short-link_is-null_goto_%s";

    /**
     * 短链接跳转前缀锁
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";
}
