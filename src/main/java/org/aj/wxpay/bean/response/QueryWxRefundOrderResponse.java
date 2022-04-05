package org.aj.wxpay.bean.response;


import org.aj.wxpay.bean.base.WxBaseResponse;
import org.aj.wxpay.constant.WxRefundStatusEnum;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 封装微信退款订单返回数据的实体类
 * @Author aj
 * @Date 2019/6/20 8:19
 **/
@XmlRootElement(name="xml")
public class QueryWxRefundOrderResponse extends WxBaseResponse {


    /**
     * <xml>
     *    <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     *    <mch_id><![CDATA[10000100]]></mch_id>
     *    <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>
     *    <out_refund_no_0><![CDATA[1415701182]]></out_refund_no_0>
     *    <out_trade_no><![CDATA[1415757673]]></out_trade_no>
     *    <refund_count>1</refund_count>
     *    <refund_fee_0>1</refund_fee_0>
     *    <refund_id_0><![CDATA[2008450740201411110000174436]]></refund_id_0>
     *    <refund_status_0><![CDATA[PROCESSING]]></refund_status_0>
     *    <result_code><![CDATA[SUCCESS]]></result_code>
     *    <return_code><![CDATA[SUCCESS]]></return_code>
     *    <return_msg><![CDATA[OK]]></return_msg>
     *    <sign><![CDATA[1F2841558E233C33ABA71A961D27561C]]></sign>
     *    <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>
     * </xml>
     */
    /**
     * 微信订单号
     * 是	String(32)
     */
    private String transactionId;

    /**
     * 商户的订单号
     * 是	String(32)
     */
    private String outTradeNo;

    /**
     *	订单总金额，单位为分
     *	是	Int
     */
    private Integer totalFee;
    /**
     *货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详
     * 	否	String(16)
     */
    private String feeType;
    /**
     *现金支付金额订单现金支付金额
     * 是	Int
     */
    private Integer cashFee;
    /**
     *现金支付货币类型
     * 否	String(16)
     */
    private String cashFeeType;

    /**
     * 当前返回退款笔数
     *是	Int
     */
    private Integer refundCount;

    /**
     * 是
     * 当前退款下标
     */
    private Integer currentRefundIndex;

    /**
     *
     * 订单总退款次数 	total_refund_count 	否 	int 	35 	订单总共已发生的部分退款次数，当请求参数传入offset后有返回
     */
    private Integer totalRefundCount;

    /**
     * 是
     * 当前这笔的退款状态
     * 退款状态：
     *
     * SUCCESS—退款成功
     *
     * REFUNDCLOSE—退款关闭，指商户发起退款失败的情况。
     *
     * PROCESSING—退款处理中
     *
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    private String currentRefundStatus;
    //refund_recv_accout
    /**
     * 当前这笔的退款时间
     * 退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
     */
    private String currentRefundTime;

    /**
     * 当前这笔退款到哪个账户上
     * 取当前退款单的退款入账方
     *
     * 1）退回银行卡：
     *
     * {银行名称}{卡类型}{卡尾号}
     *
     * 2）退回支付用户零钱:
     *
     * 支付用户零钱
     *
     * 3）退还商户:
     *
     * 商户基本账户
     *
     * 商户结算银行账户
     *
     * 4）退回支付用户零钱通:
     *
     * 支付用户零钱通
     */
    private String currentRefundRecdAccount;


    /*  *//**
     *商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * 是	String(64)
     *//*
    private String out_refund_no_0;

    *//**
     *微信退款单号
     * 是	String(32)
     *//*
    private String refund_id_0;

    *//**
     *ORIGINAL—原路退款
     *
     * BALANCE—退回到余额
     *
     * OTHER_BALANCE—原账户异常退到其他余额账户
     *
     * OTHER_BANKCARD—原银行卡异常退到其他银行卡
     * 否	String(16)
     *//*
    private String refund_channel_0;

    *//**
     *退款总金额,单位为分,可以做部分退款
     * 是	Int
     *//*
    private String refund_fee_0;

    *//**
     *
     * 退款状态：
     *
     * SUCCESS—退款成功
     *
     * REFUNDCLOSE—退款关闭。
     *
     * PROCESSING—退款处理中
     *
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     * 是	String(16)
     *//*
    private String refund_status_0;

    *//**
     *REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户
     *
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
     *
     * $n为下标，从0开始编号
     * 否	String(30)
     *//*
    private String 	refund_account_0;

    *//**
     *	取当前退款单的退款入账方
     * 1）退回银行卡：
     *
     * {银行名称}{卡类型}{卡尾号}
     *
     * 2）退回支付用户零钱:
     *
     * 支付用户零钱
     *
     * 3）退还商户:
     *
     * 商户基本账户
     *
     * 商户结算银行账户
     *
     * 4）退回支付用户零钱通:
     *
     * 支付用户零钱通
     * 是	String(64)
     *//*
    private String refund_recv_accout_0;

    *//**
     *退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
     * 否	String(20)
     *//*
    private String refund_success_time_0;
*/

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;

    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;

    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;

    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;

    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;

    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;

    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;

    }

    public String getCurrentRefundStatus() {
        return currentRefundStatus;
    }

    public void setCurrentRefundStatus(String currentRefundStatus) {
        this.currentRefundStatus = currentRefundStatus;

    }

    public String getCurrentRefundTime() {
        return currentRefundTime;
    }

    public void setCurrentRefundTime(String currentRefundTime) {
        this.currentRefundTime = currentRefundTime;

    }

    public String getCurrentRefundRecdAccount() {
        return currentRefundRecdAccount;
    }

    public void setCurrentRefundRecdAccount(String currentRefundRecdAccount) {
        this.currentRefundRecdAccount = currentRefundRecdAccount;

    }

    public Integer getCurrentRefundIndex() {
        return currentRefundIndex;
    }

    public void setCurrentRefundIndex(Integer currentRefundIndex) {
        this.currentRefundIndex = currentRefundIndex;

    }

    public Integer getTotalRefundCount() {
        return totalRefundCount;
    }

    public void setTotalRefundCount(Integer totalRefundCount) {
        this.totalRefundCount = totalRefundCount;

    }

    /**
     * @description 判断退款是否成功
     * @author aj
     * @date 2022/1/30 18:29
     * @param
     * @return boolean
     */
    public  boolean isRefundSuccess(){
        if(isBusinessSuccess() && isRequestSuccess()){
            if(StringUtils.isNotBlank(this.currentRefundStatus)
                    && WxRefundStatusEnum.SUCCESS.toString().equalsIgnoreCase(this.currentRefundStatus)){
                return  true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "QueryWxRefundOrderResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalFee=" + totalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                ", refundCount=" + refundCount +
                ", currentRefundIndex=" + currentRefundIndex +
                ", totalRefundCount=" + totalRefundCount +
                ", currentRefundStatus='" + currentRefundStatus + '\'' +
                ", currentRefundTime='" + currentRefundTime + '\'' +
                ", currentRefundRecdAccount='" + currentRefundRecdAccount + '\'' +
                "} " + super.toString();
    }
}
