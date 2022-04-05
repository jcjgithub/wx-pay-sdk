package org.aj.wxpay.bean.notify;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jcj
 * @description 封装微信要求的通知接口返回值
 * @date 2022/2/11 15:16
 */
@XmlRootElement(name = "xml")
public class NotifyResponse {

   public static final String SUCCESS="SUCCESS";
   public static final String SUCCESS_MSG="OK";
   public static final String FAIL="FAIL";

    //<xml>
    //  <return_code><![CDATA[SUCCESS]]></return_code>
    //  <return_msg><![CDATA[OK]]></return_msg>
    //</xml>
    /**
     * 返回状态码 	return_code 	是 	String(16) 	SUCCESS
     *
     * SUCCESS/FAIL
     *
     * SUCCESS表示商户接收通知成功并校验成功
     */
    private String returnCode;

    /**
     * 返回信息 	return_msg 	否 	String(128) 	OK
     *
     * 返回信息，如非空，为错误原因：
     *
     * 签名失败
     *
     * 参数格式校验错误
     */
    private String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }
    @XmlElement(name = "return_code")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }
    @XmlElement(name = "return_msg")
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public static NotifyResponse wrapSuccess(){
        NotifyResponse notifyResponse = new NotifyResponse();
        notifyResponse.setReturnCode(SUCCESS);
        notifyResponse.setReturnMsg(SUCCESS_MSG);
        return notifyResponse;
    }
    public static NotifyResponse wrapError(String errorMsg){
        if(StringUtils.isBlank(errorMsg))
            throw new IllegalArgumentException("errorMsg must not empty");
        NotifyResponse notifyResponse = new NotifyResponse();
        notifyResponse.setReturnCode(FAIL);
        notifyResponse.setReturnMsg(errorMsg);
        return notifyResponse;
    }
}
