package org.aj.wxpay.bean.response;


import org.aj.wxpay.bean.base.WxBaseResponse;
import org.aj.wxpay.constant.WxPayStatusEnum;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 封装查询微信支付的订单返回的数据的实体类
 * @Author aj
 * @Date 2019/6/20 8:10
 **/
@XmlRootElement(name="xml")
public class QueryWxPayOrderResponse extends WxBaseResponse {
    /**
     * <xml>
     *    <return_code><![CDATA[SUCCESS]]></return_code>
     *    <return_msg><![CDATA[OK]]></return_msg>
     *    <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     *    <mch_id><![CDATA[10000100]]></mch_id>
     *    <device_info><![CDATA[1000]]></device_info>
     *    <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>
     *    <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign>
     *    <result_code><![CDATA[SUCCESS]]></result_code>
     *    <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>
     *    <is_subscribe><![CDATA[Y]]></is_subscribe>
     *    <trade_type><![CDATA[APP]]></trade_type>
     *    <bank_type><![CDATA[CCB_DEBIT]]></bank_type>
     *    <total_fee>1</total_fee>
     *    <fee_type><![CDATA[CNY]]></fee_type>
     *    <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>
     *    <out_trade_no><![CDATA[1415757673]]></out_trade_no>
     *    <attach><![CDATA[订单额外描述]]></attach>
     *    <time_end><![CDATA[20141111170043]]></time_end>
     *    <trade_state><![CDATA[SUCCESS]]></trade_state>
     * </xml>
     */

    /**
     *微信支付分配的终端设备号，
     * 否	String(32)
     */
    private String deviceInfo;
    /**
     *用户在商户appid下的唯一标识
     * 是	String(128)
     */
    private String openid;
    /**
     *	用户是否关注公众账号，Y-关注，N-未关注
     *是	String(1)
     */
    private String isSubscribe;
    /**
     *调用接口提交的交易类型
     * 是	String(16)
     */
    private String tradeType;
    /**
     *交易状态
     *
     * SUCCESS—支付成功
     *
     * REFUND—转入退款
     *
     * NOTPAY—未支付
     *
     * CLOSED—已关闭
     *
     * REVOKED—已撤销（刷卡支付）
     *
     * USERPAYING--用户支付中
     *
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     * 	是	String(32)
     */
    private String tradeState;
    /**
     *	银行类型，采用字符串类型的银行标识
     *	是	String(16)
     */
    private String bankType;


    /**
     *附加数据，原样返回
     * 	否	String(128)
     */
    private String attach;

    /**
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见
     *  是	String(14)
     */
    private String timeEnd;

    /**
     *对当前查询订单状态的描述和下一步操作的指引
     * 是	String(256)
     */
    private String tradeStateDesc;


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

    public String getDeviceInfo() {
        return deviceInfo;
    }
    @XmlElement(name = "device_info")
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;

    }

    public String getIsSubscribe() {
        return isSubscribe;
    }
    @XmlElement(name = "is_subscribe")
    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;

    }

    public String getTradeType() {
        return tradeType;
    }
    @XmlElement(name = "trade_type")
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;

    }

    public String getTradeState() {
        return tradeState;
    }
    @XmlElement(name = "trade_state")
    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;

    }

    public String getBankType() {
        return bankType;
    }
    @XmlElement(name = "bank_type")
    public void setBankType(String bankType) {
        this.bankType = bankType;

    }

    public String getTimeEnd() {
        return timeEnd;
    }
    @XmlElement(name = "time_end")
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;

    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }
    @XmlElement(name = "trade_state_desc")
    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;

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

    public Integer getTotalFee() {
        return totalFee;
    }
    @XmlElement(name = "total_fee")
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;

    }

    public String getFeeType() {
        return feeType;
    }
    @XmlElement(name = "fee_type")
    public void setFeeType(String feeType) {
        this.feeType = feeType;

    }

    public Integer getCashFee() {
        return cashFee;
    }
    @XmlElement(name = "cash_fee")
    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;

    }

    public String getCashFeeType() {
        return cashFeeType;
    }
    @XmlElement(name = "cash_fee_type")
    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;

    }


    public String getOpenid() {
        return openid;
    }
    @XmlElement(name = "openid")
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAttach() {
        return attach;
    }
    @XmlElement(name = "attach")
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * @description 判断支付是否成功
     * @author aj
     * @date 2022/1/30 18:29
     * @param
     * @return boolean
     */
    public  boolean isPaySuccess(){
        if(isBusinessSuccess() && isRequestSuccess()){
            if(StringUtils.isNotBlank(this.tradeState)
                    && WxPayStatusEnum.SUCCESS.toString().equalsIgnoreCase(this.tradeState)){
                return  true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "QueryWxPayOrderResponse{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", openid='" + openid + '\'' +
                ", isSubscribe='" + isSubscribe + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", tradeState='" + tradeState + '\'' +
                ", bankType='" + bankType + '\'' +
                ", attach='" + attach + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", tradeStateDesc='" + tradeStateDesc + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalFee=" + totalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                "} " + super.toString();
    }
}
