package org.aj.wxpay.exception;

/**
 * @autor aj
 * @description 处理调用接口的时候发生的异常
 * @date 2022/4/421:25
 */
public class ApiException extends RuntimeException {
    /**
     * 业务错误码 取得是微信返回的错误码
     */
    private String code;

    /**
     * 取得是微信返回的错误信息
     */
    private String msg;

    public ApiException(String message) {
        super(message);
        this.msg = message;
    }

    public ApiException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiException(String code, String msg,Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;

    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
