package org.aj.wxpay.bean.notify;




import org.aj.wxpay.bean.base.WxBaseResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WxPayNotify extends WxBaseResponse {



    /**
     * 微信支付分配的终端设备号
     */
    //// @JacksonXmlProperty(localName = "device_info")
    private String deviceInfo;


    /**
     * 用户在商户appid下的唯一标识
     */
   // // @JacksonXmlProperty(localName = "openid")
    private String openid;

    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    //// @JacksonXmlProperty(localName = "is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型  	JSAPI、NATIVE、APP
     */
    //// @JacksonXmlProperty(localName = "trade_type")
    private String tradeType;

    /**
     * 银行类型，采用字符串类型的银行标识
     */
    // @JacksonXmlProperty(localName = "bank_type")
    private String bankType;

    /**
     * 订单总金额，单位为分
     */
    // @JacksonXmlProperty(localName = "total_fee")
    private Integer totalFee;

    /**
     * 货币类型，符合ISO4217标准的三位字母代码
     */
    // @JacksonXmlProperty(localName = "fee_type")
    private String feeType;

    /**
     * 现金支付金额订单现金支付金额
     */
    // @JacksonXmlProperty(localName = "cash_fee")
    private Integer cashFee;

    /**
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    // @JacksonXmlProperty(localName = "cash_fee_type")
    private String cashFeeType;

    /**
     * 微信支付订单号
     */
    // @JacksonXmlProperty(localName = "transaction_id")
    private String transactionId;

    /**
     * 本系统的订单号
     */
    // @JacksonXmlProperty(localName = "out_trade_no")
    private String outTradeNo;

    /**
     * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    // @JacksonXmlProperty(localName = "time_end")
    private String timeEnd;







    private static final long serialVersionUID = 1L;


    /**
     * 获取微信支付分配的终端设备号
     *
     * @return device_info - 微信支付分配的终端设备号
     */
    public String getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * 设置微信支付分配的终端设备号
     *
     * @param deviceInfo 微信支付分配的终端设备号
     */
    @XmlElement(name = "device_info")
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
    }


    /**
     * 获取用户在商户appid下的唯一标识
     *
     * @return openid - 用户在商户appid下的唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户在商户appid下的唯一标识
     *
     * @param openid 用户在商户appid下的唯一标识
     */
    @XmlElement(name = "openid")
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 获取用户是否关注公众账号，Y-关注，N-未关注
     *
     * @return is_subscribe - 用户是否关注公众账号，Y-关注，N-未关注
     */
    public String getIsSubscribe() {
        return isSubscribe;
    }

    /**
     * 设置用户是否关注公众账号，Y-关注，N-未关注
     *
     * @param isSubscribe 用户是否关注公众账号，Y-关注，N-未关注
     */
    @XmlElement(name = "is_subscribe")
    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe == null ? null : isSubscribe.trim();
    }

    /**
     * 获取交易类型
     *
     * @return trade_type - 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置交易类型
     *
     * @param tradeType 交易类型
     */
    @XmlElement(name = "trade_type")
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 获取银行类型，采用字符串类型的银行标识
     *
     * @return bank_type - 银行类型，采用字符串类型的银行标识
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * 设置银行类型，采用字符串类型的银行标识
     *
     * @param bankType 银行类型，采用字符串类型的银行标识
     */
    @XmlElement(name = "bank_type")
    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    /**
     * 获取订单总金额，单位为分
     *
     * @return total_fee - 订单总金额，单位为分
     */
    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * 设置订单总金额，单位为分
     *
     * @param totalFee 订单总金额，单位为分
     */
    @XmlElement(name = "total_fee")
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取货币类型，符合ISO4217标准的三位字母代码
     *
     * @return fee_type - 货币类型，符合ISO4217标准的三位字母代码
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置货币类型，符合ISO4217标准的三位字母代码
     *
     * @param feeType 货币类型，符合ISO4217标准的三位字母代码
     */
    @XmlElement(name = "fee_type")
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 获取现金支付金额订单现金支付金额
     *
     * @return cash_fee - 现金支付金额订单现金支付金额
     */
    public Integer getCashFee() {
        return cashFee;
    }

    /**
     * 设置现金支付金额订单现金支付金额
     *
     * @param cashFee 现金支付金额订单现金支付金额
     */
    @XmlElement(name = "cash_fee")
    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    /**
     * 获取货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     *
     * @return cash_fee_type - 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    public String getCashFeeType() {
        return cashFeeType;
    }

    /**
     * 设置货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     *
     * @param cashFeeType 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    @XmlElement(name = "cash_fee_type")
    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType == null ? null : cashFeeType.trim();
    }

    /**
     * 获取微信支付订单号
     *
     * @return transaction_id - 微信支付订单号
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设置微信支付订单号
     *
     * @param transactionId 微信支付订单号
     */
    @XmlElement(name = "transaction_id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    /**
     * 获取本系统的订单号
     *
     * @return out_trade_no - 本系统的订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置本系统的订单号
     *
     * @param outTradeNo 本系统的订单号
     */
    @XmlElement(name = "out_trade_no")
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * 获取支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     *
     * @return time_end - 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * 设置支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     *
     * @param timeEnd 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    @XmlElement(name = "time_end")
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd == null ? null : timeEnd.trim();
    }


    @Override
    public String toString() {
        return "WxPayNotify{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", openid='" + openid + '\'' +
                ", isSubscribe='" + isSubscribe + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", bankType='" + bankType + '\'' +
                ", totalFee=" + totalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                "} " + super.toString();
    }
}