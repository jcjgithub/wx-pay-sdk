package org.aj.wxpay.bean.request;



import org.aj.wxpay.util.ValidateUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 创建支付链接的需要传入的参数
 * 关于注释例如 是	String(32) 表示此参数是必传参数，String(32)表示最多是32个的字符
 * <p>文档地址：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1</p>
 * @Author aj
 * @Date 2019/6/14 15:28
 **/
@XmlRootElement(name="xml")
public class CreateWxPayLinkRequestVo {




    /**
     *终端设备号(门店号或收银设备ID)，默认请传"WEB"
     * 否	String(32)
     */
    private String deviceInfo="WEB";




    /**
     *商品描述交易字段格式根据不同的应用场景按照以下格式：
     *
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值
     * 是	String(128)
     */
    private String body;
    /**
     *	商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
     *否	String(8192)
     */
    private String detail;
    /**
     *附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     * 	否	String(127)
     */
    private String attach;
    /**
     *商户系统内部订单号，要求32个字符内（最少6个字符）只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
     * 是	String(32)
     */
    private String outTradeNo;
    /**
     *符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * 	否	String(16)
     */
    private String feeType;
    /**
     *	订单总金额，单位为分，详见支付金额
     *是	 Int
     */
    private Integer totalFee;

    /**
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     * 是	String(64)
     */
    private String spbillCreateIp;
    /**
     *订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * 	否	String(14)
     */
    private String timeStart;



    /**
     *订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
     * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     *
     * 建议：最短失效时间间隔大于1分钟
     * 	否	String(14)
     */
    private String timeExpire;



    /**
     *订单优惠标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
     * 	否	String(32)
     */
    private String goodsTag;
    /**
     *接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     * 是	String(256)
     */
    private String notifyUrl;
    /**
     *支付类型
     *  	是 	String(16)
     *  com.iotimc.yimi.pay.constant.WxTradeType
     *  按照这里面的取值
     */
    private String tradeType="APP";
    /**
     *	no_credit--指定不能使用信用卡支付
     *	否	String(32)
     */
    private String limitPay;



    /**
     *Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     * 	否	String(8)
     */
    private String receipt;


    /**
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     * 否 	String(32)
     */
    private String productId;


    public CreateWxPayLinkRequestVo(String body,  String outTradeNo, Integer totalFee, String spbillCreateIp,  String timeStart, String notifyUrl) {

        ValidateUtils.validateString(body,"body");
        ValidateUtils.validateString(outTradeNo,"outTradeNo");
        ValidateUtils.validateString(spbillCreateIp,"spbillCreateIp");
        ValidateUtils.validateString(notifyUrl,"notifyUrl");
        ValidateUtils.validateFee(totalFee,"totalFee");
        ValidateUtils.validateTimeStart(timeStart);
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.spbillCreateIp = spbillCreateIp;
        this.timeStart = timeStart;
        this.notifyUrl = notifyUrl;
    }


    public String getDeviceInfo() {
        return deviceInfo;
    }




    public String getBody() {
        return body;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;

    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;

    }

    public String getOutTradeNo() {
        return outTradeNo;
    }


    public String getFeeType() {
        return feeType;
    }
    @XmlElement(name = "fee_type")
    public void setFeeType(String feeType) {
        this.feeType = feeType;

    }

    public Integer getTotalFee() {
        return totalFee;
    }


    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }


    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }
    @XmlElement(name = "time_expire")
    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;

    }

    public String getGoodsTag() {
        return goodsTag;
    }
    @XmlElement(name = "goods_tag")
    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;

    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }
    @XmlElement(name = "trade_type")
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;

    }


    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;

    }

    public String getProductId() {
        return productId;
    }
    @XmlElement(name = "product_id")
    public void setProductId(String productId) {
        this.productId = productId;

    }
    public String getLimitPay() {
        return limitPay;
    }
    @XmlElement(name = "limit_pay")
    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    @Override
    public String toString() {
        return "CreateWxPayLinkRequestVo{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", feeType='" + feeType + '\'' +
                ", totalFee=" + totalFee +
                ", spbillCreateIp='" + spbillCreateIp + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeExpire='" + timeExpire + '\'' +
                ", goodsTag='" + goodsTag + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", limitPay='" + limitPay + '\'' +
                ", receipt='" + receipt + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
