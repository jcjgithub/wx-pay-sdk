package org.aj.wxpay.bean.response;

import java.io.Serializable;

/**
 * @Description 属性命名规则按照微信的支付的文档命名 因此就没用驼峰了
 * 免得相互转来转去
 * @Author aj
 * @Date 2019/6/15 9:58
 **/
public class CreateWxPayAppLinkResponse implements Serializable {

    private static final long serialVersionUID = -5735896981489043700L;

    /**
     *微信开放平台审核通过的应用APPID
     * 	String(32)	是
     * 	加上这个注解是因为，此appid字段信息比较敏感，即尽量避免
     * 	在网络中进行传输
     */
   // @JsonIgnore
    private String appid;

    /**
     *微信支付分配的商户号
     * String(32)	是
     */
    private String partnerid;
    /**
     *信返回的支付交易会话ID
     *String(32)	是
     */
    private String prepayid;

    /**
     *	暂填写固定值Sign=WXPay
     *  这里不命名成package是因为 package是关键词
     */
   // @JsonProperty(value = "pagckage")
    private String packageExt="Sign=WXPay";
    /**
     *随机字符串，不长于32位
     */
    private String noncestr;
    /**
     *时间戳，请见接口规则-参数规定
     * 	String(10)	是
     * 	标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)
     */
    private String timestamp;
    /**
     * 签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致
     *String(32)	是
     */
    private String sign;



    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageExt() {
        return packageExt;
    }

    public String getPackage() {
        return packageExt;
    }

    public void setPackageExt(String packageExt) {
        this.packageExt = packageExt;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public CreateWxPayAppLinkResponse() {
    }


    public CreateWxPayAppLinkResponse(String appid, String partnerid, String prepayid, String noncestr, String timestamp, String sign) {
        this.appid = appid;
        this.partnerid = partnerid;
        this.prepayid = prepayid;
        this.noncestr = noncestr;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "CreateWxPayAppLinkResponse{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", packageExt='" + packageExt + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
