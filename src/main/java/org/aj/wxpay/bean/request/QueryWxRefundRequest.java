package org.aj.wxpay.bean.request;


import cn.hutool.core.util.StrUtil;
import org.aj.wxpay.bean.base.WxBaseRequest;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 申请退款请求体以及查询退款订单的请求体  否	String(32)  "否”表示是该参数不是必传得，“String（32）"表示是最多是32得长度
 * 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
 * 注意：如果单个支付订单部分退款次数超过20次请使用退款单号查询
 *           当退款订单时间超过一年半，调用查询退款接口时，需同时使用退款订单号和微信退款订单号查询或者单独使用微信订单号查询
 * @Author aj
 * @Date 2019/6/17 15:00
 **/
@XmlRootElement(name="xml")
public class QueryWxRefundRequest extends WxBaseRequest {



    /**
     * 微信生成的订单号，在支付通知中有返回
     * String(32) 跟下面得参数是四选一
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
     * 微信退款单号 	refund_id 	String(32) 	1217752501201407033233368018
     *
     * 微信生成的退款单号，在申请退款接口有返回
     */
    private String refundId;

    /**
     * 四选一
     * 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
     * 如果单个支付订单部分退款次数超过20次请使用退款单号查询
     *  当退款订单时间超过一年半，调用查询退款接口时，需同时使用退款订单号和微信退款订单号查询或者单独使用微信订单号查询
     * @param transactionId
     * @param outTradeNo
     * @param outRefundNo
     * @param refundId
     */
    public QueryWxRefundRequest(String transactionId, String outTradeNo,  String outRefundNo, String refundId) {
        if(StrUtil.isAllBlank(transactionId,outTradeNo,outRefundNo,refundId)){
            throw new IllegalArgumentException("Parameters cannot all be empty");
        }
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.outRefundNo = outRefundNo;
        this.refundId = refundId;
    }

    public QueryWxRefundRequest(QueryWxRefundRequestVo queryWxRefundRequest) {
        this.transactionId = queryWxRefundRequest.getTransactionId();
        this.outTradeNo = queryWxRefundRequest.getOutTradeNo();
        this.outRefundNo = queryWxRefundRequest.getOutRefundNo();
        this.refundId = queryWxRefundRequest.getRefundId();
    }

    public QueryWxRefundRequest() {
    }

    /**
     * 根据商户订单号构建一个对象
     * @param outTradeNo
     * @return
     */
    public static QueryWxRefundRequest buildByOutTradNo(String outTradeNo){
        return new QueryWxRefundRequest(null,outTradeNo,null,null);
    }
    /**
     * 根据 微信的订单号构建一个对象
     * @param transactionId
     * @return
     */
    public static QueryWxRefundRequest buildByTransactionId(String transactionId){
        return new QueryWxRefundRequest(transactionId,null,null,null);
    }

    /**
     * 根据 商户的退款单号构建一个对象
     * @param outRefundNo
     * @return
     */
    public static QueryWxRefundRequest buildByOutRefundNo(String outRefundNo){
        return new QueryWxRefundRequest(null,null,outRefundNo,null);
    }

    /**
     * 根据 微信生成的退款单号，在申请退款接口有返回 构建一个对象
     * @param refundId
     * @return
     */
    public static QueryWxRefundRequest buildByOutRefundId(String refundId){
        return new QueryWxRefundRequest(null,null,null,refundId);
    }

    public String getTransactionId() {
        return transactionId;
    }
    @XmlElement(name = "setTransaction_id")
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


    public String getRefundId() {
        return refundId;
    }
    @XmlElement(name = "refund_id")
    public void setRefundId(String refundId) {
        this.refundId = refundId;

    }

    @Override
    public String toString() {
        return "QueryWxRefundRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", refundId='" + refundId + '\'' +
                "} " + super.toString();
    }
}
