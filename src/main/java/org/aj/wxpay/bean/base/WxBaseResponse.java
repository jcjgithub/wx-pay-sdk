package org.aj.wxpay.bean.base;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * 调用微信支付平台各接口封装返回数据的基类
 * <P>需要注意的由于这里面包含了一些敏感的数据例如：appid，对于appid尽量不要返回给前端，内部处理即可
 *  此处为基类 由于使用forest客户端的原因 请不要携带JAXB的注解@XmlRootElement(name="xml")
 *  否则会报类型转换异常
 * </P>
 */
public class WxBaseResponse implements Serializable {

    private static final long serialVersionUID = 4575372757073076655L;


    private String appid;
    /**
     *返回状态码
     * SUCCESS/FAIL
     *
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
     * 是	String(16)
     */
    private String returnCode;

    /**
     *返回信息
     *
     * 返回信息，如非空，为错误原因
     *
     * 签名失败
     *
     * 参数格式校验错误
     * 否	String(128)
     */
    private String returnMsg;

    /**
     *微信支付分配的商户号
     * 	是	String(32)
     */
    private String mchId;

    /**
     *随机字符串，不长于32
     * nonce_str	是
     */
    private String nonceStr;

    /**
     *
     */
    private String sign;

    /**
     *业务结果  SUCCESS/FAIL
     */
    private String resultCode;

    /**
     *错误代码
     * 	否	String(32)
     */
    private String errCode;

    /**
     *错误代码描述
     * 	否	String(128)
     */
    private String errCodeDes;

    /**
     * 保持没有解析之前的xml数据
     */
    private String body;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }


    public String getReturnCode() {
        return returnCode;
    }

    @XmlElement(name = "return_code")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
      
    }

/*    @XmlElement(name = "return_code")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        //return this;
    }*/
    public String getReturnMsg() {
        return returnMsg;
    }

    @XmlElement(name = "return_msg")
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
      
    }

    public String getMchId() {
        return mchId;
    }
    @XmlElement(name = "mch_id")
    public void setMchId(String mchId) {
        this.mchId = mchId;
      
    }

    public String getNonceStr() {
        return nonceStr;
    }
    @XmlElement(name = "nonce_str")
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
      
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
      
    }

    public String getResultCode() {
        return resultCode;
    }
    @XmlElement(name = "result_code")
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
      
    }

    public String getErrCode() {
        return errCode;
    }
    @XmlElement(name = "err_code")
    public void setErrCode(String errCode) {
        this.errCode = errCode;
      
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }
    @XmlElement(name = "err_code_des")
    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
      
    }

    /**
     * 表示请求是否成功
     * @return
     */
    public boolean isRequestSuccess(){
        if(this.returnCode!=null && !"".equals(returnCode.trim())){
            if("SUCCESS".equalsIgnoreCase(returnCode.trim())){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 请求的业务是否成功
     * @return
     */
    public boolean isBusinessSuccess(){
        if(isRequestSuccess()){
            if(this.resultCode!=null && !"".equals(resultCode.trim())){
                if("SUCCESS".equalsIgnoreCase(resultCode.trim())){
                    return true;
                }
                return false;
            }
            return false;
        }
        return  false;
    }

    @Override
    public String toString() {
        return "WxBaseResponse{" +
                "appid='" + appid + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", mchId='" + mchId + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", sign='" + sign + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errCodeDes='" + errCodeDes + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
