package org.aj.wxpay.bean.response;




import org.aj.wxpay.bean.base.WxBaseResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <h3>battery_server</h3>
 * <p>封装调用退款接口返回的数据</p>
 * 目前只封装必要的参数
 * @author : jcj
 * @date : 2019-06-29 20:25
 **/
@XmlRootElement(name="xml")
public class WxRefundResponse extends WxBaseResponse {


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
     * 是 	String(32) 	2007752501201407033233368018 	微信退款单号
     */
    private String refundId;

    /**
     * 是 	int 	100 	退款总金额,单位为分,可以做部分退款
     */
    private String refundFee;

    /**
     * 商户退款单号 	out_refund_no 	是 	String(64) 	121775250 	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     *
     */
    private String outRefundNo;

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

    public String getRefundId() {
        return refundId;
    }
    @XmlElement(name = "refund_id")
    public void setRefundId(String refundId) {
        this.refundId = refundId;

    }

    public String getRefundFee() {
        return refundFee;
    }
    @XmlElement(name = "refund_fee")
    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;

    }

    public String getOutRefundNo() {
        return outRefundNo;
    }
    @XmlElement(name = "out_refund_no")
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    @Override
    public String toString() {
        return "WxRefundResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", refundFee='" + refundFee + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                "} " + super.toString();
    }
}
