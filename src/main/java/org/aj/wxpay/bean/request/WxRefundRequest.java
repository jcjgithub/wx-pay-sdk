package org.aj.wxpay.bean.request;


import cn.hutool.core.util.StrUtil;
import org.aj.wxpay.bean.base.WxBaseRequest;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 申请退款请求体以及查询退款订单的请求体  否	String(32)  "否”表示是该参数不是必传得，“String（32）"表示是最多是32得长度
 * @Author aj
 * @Date 2019/6/17 15:00
 **/
@XmlRootElement(name="xml")
public class WxRefundRequest extends WxBaseRequest {

    /**
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     * 否	String(32) 内部会赋值不需要动态传入
     */
    private String signType;

    /**
     * 微信生成的订单号，在支付通知中有返回
     * String(32) 跟下面得参数是二选一
     */
    private String transactionId;
    /**
     *商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * String(32)
     */
    private String outTradeNo;

    /**
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔
     * 是	String(64)
     */
    private String outRefundNo;

    /**
     * 订单总金额，单位为分，只能为整数，详见支付金额
     * 是	Int
     */
    private Integer totalFee;

    /**
     * 	退款总金额，订单总金额，单位为分，只能为整数
     * 	是	Int
     */
    private Integer refundFee;

    /**
     * 退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY
     * 	否	String(8)
     */
    private String 	refundFeeType;

    /**
     * 若商户传入，会在下发给用户的退款消息中体现退款原因
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     * 否	String(80) 虽然微信要求不传 我们这边还是强制要求用户传入
     */
    private String refundDesc;

    /**
     * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     *
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
     * 	否	String(256) 虽然微信要求不传 我们这边还是强制要求用户传入
     */
    private String notifyUrl;

    public WxRefundRequest(String transactionId, String outTradeNo,  String outRefundNo, Integer totalFee,Integer refundFee,  String refundDesc, String notifyUrl) {
        if(StrUtil.isAllBlank(transactionId,outRefundNo)){
            throw new IllegalArgumentException("transactionId outTradeNo cannot both be empty");
        }

        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.outRefundNo = outRefundNo;
        this.totalFee = totalFee;
        this.refundFee = refundFee;
        this.refundDesc = refundDesc;
        this.notifyUrl = notifyUrl;
    }

    public WxRefundRequest() {
    }

    public String getSignType() {
        return signType;
    }
    @XmlElement(name = "sign_type")
    public void setSignType(String signType) {
        this.signType = signType;

    }

    public String getTransactionId() {
        return transactionId;
    }
    @XmlElement(name = "transaction_id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;

    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
    @XmlElement(name = "out_trade_no")
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;

    }

    public String getOutRefundNo() {
        return outRefundNo;
    }
    @XmlElement(name = "out_refund_no")
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;

    }

    public Integer getTotalFee() {
        return totalFee;
    }
    @XmlElement(name = "total_fee")
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;

    }

    public Integer getRefundFee() {
        return refundFee;
    }
    @XmlElement(name = "refund_fee")
    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;

    }

    public String getRefundFeeType() {
        return refundFeeType;
    }
    @XmlElement(name = "refund_fee_type")
    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;

    }

    public String getRefundDesc() {
        return refundDesc;
    }
    @XmlElement(name = "refund_desc")
    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;

    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
    @XmlElement(name = "notify_url")
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;

    }

    @Override
    public String toString() {
        return "WxRefundRequest{" +
                "signType='" + signType + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", totalFee=" + totalFee +
                ", refundFee=" + refundFee +
                ", refundFeeType='" + refundFeeType + '\'' +
                ", refundDesc='" + refundDesc + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                "} " + super.toString();
    }
}
