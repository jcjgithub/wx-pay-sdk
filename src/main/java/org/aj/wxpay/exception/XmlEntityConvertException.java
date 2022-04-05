package org.aj.wxpay.exception;

/**
 * @autor aj
 * @description 记录xml跟实体类之间的转换异常
 * @date 2022/4/421:13
 */
public class XmlEntityConvertException extends RuntimeException {

    public XmlEntityConvertException(String message) {
        super(message);
    }

    public XmlEntityConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlEntityConvertException(Throwable cause) {
        super(cause);
    }
}
