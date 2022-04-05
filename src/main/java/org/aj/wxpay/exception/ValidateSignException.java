package org.aj.wxpay.exception;

/**
 * @autor aj
 * @description 验签异常
 * @date 2022/4/420:57
 */
public class ValidateSignException extends RuntimeException {


    public ValidateSignException() {
    }

    public ValidateSignException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateSignException(Throwable cause) {
        super(cause);
    }


}
