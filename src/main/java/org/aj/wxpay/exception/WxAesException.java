package org.aj.wxpay.exception;

/**
 * @autor aj
 * @description 微信AES加解密异常
 * @date 2022/4/421:08
 */
public class WxAesException extends RuntimeException {

    public WxAesException(String message) {
        super(message);
    }

    public WxAesException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxAesException(Throwable cause) {
        super(cause);
    }
}
