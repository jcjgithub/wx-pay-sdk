package org.aj.wxpay.bean.request;



import org.aj.wxpay.bean.base.WxBaseRequest;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 查询微信支付订单需要传递的参数
 * @Author aj
 * @Date 2019/6/17 10:40
 **/
@XmlRootElement(name="xml")
public class QueryWxPayOrderRequest extends WxBaseRequest {

    /**
     * 微信的订单号，优先使用
     * String(32)
     */
    private String transactionId;

    /**
     * 	商户系统内部的订单号，当没提供transaction_id时需要传这个
     * 	String(32)
     */
    private String outTradeNo;

    @XmlElement(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

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

    /**
     * 二选一
     * @param transactionId
     * @param outTradeNo
     */
    public QueryWxPayOrderRequest(String transactionId, String outTradeNo) {
        if(StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)){
            throw new IllegalArgumentException("Parameters cannot all be empty");
        }
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;

    }

    public QueryWxPayOrderRequest() {
    }

    /**
     * 根据商户订单号构建一个对象
     * @param outTradeNo
     * @return
     */
    public static QueryWxPayOrderRequest buildByOutTradNo(String outTradeNo){
        return new QueryWxPayOrderRequest(null,outTradeNo);
    }
    /**
     * 根据 微信的订单号构建一个对象
     * @param transactionId
     * @return
     */
    public static QueryWxPayOrderRequest buildByTransactionId(String transactionId){
        return new QueryWxPayOrderRequest(null,transactionId);
    }

    @Override
    public String toString() {
        return "QueryWxPayOrderRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                "} " + super.toString();
    }
}
