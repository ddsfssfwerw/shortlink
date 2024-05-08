package org.example.shortlink.admin.common.convention.errorcode;

/**
 * @author LLY
 * @InterfaceName IErrorCode
 * @date 2024/5/8
 */
/**
 * 平台错误码
 */
public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}
