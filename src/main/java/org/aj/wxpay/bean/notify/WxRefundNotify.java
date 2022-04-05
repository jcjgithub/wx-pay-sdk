package org.aj.wxpay.bean.notify;




import org.aj.wxpay.bean.base.WxBaseResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WxRefundNotify extends WxBaseResponse {



    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 商户系统内部的订单号
     */
    private String outTradeNo;

    /**
     * 微信退款单号
     */
    private String refundId;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 订单总金额，单位为分，只能为整数
     */
    private Integer totalFee;

    /**
     * 当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    private Integer settlementTotalFee;

    /**
     * 退款总金额,单位为分
     */
    private Integer refundFee;

    /**
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    private Integer settlementRefundFee;

    /**
     * SUCCESS-退款成功
        CHANGE-退款异常
        REFUNDCLOSE—退款关闭
     */
    private String refundStatus;

    /**
     * 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     */
    private String successTime;

    /**
     * 取当前退款单的退款入账方

        1）退回银行卡：

        {银行名称}{卡类型}{卡尾号}

        2）退回支付用户零钱:

        支付用户零钱

        3）退还商户:

        商户基本账户

        商户结算银行账户

        4）退回支付用户零钱通:

        支付用户零钱通
             */
    private String refundRecvAccout;

    /**
     * REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     */
    private String refundAccount;

    /**
     * 	
API接口
VENDOR_PLATFORM商户平台
     */
    private String refundRequestSource;



    /**
     * 加密信息请用商户秘钥进行解密，
     */
    private String reqInfo;

    private static final long serialVersionUID = 1L;


    /**
     * 获取微信订单号
     *
     * @return transaction_id - 微信订单号
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设置微信订单号
     *
     * @param transactionId 微信订单号
     */
    @XmlElement(name = "transaction_id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    /**
     * 获取商户系统内部的订单号
     *
     * @return out_trade_no - 商户系统内部的订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置商户系统内部的订单号
     *
     * @param outTradeNo 商户系统内部的订单号
     */
    @XmlElement(name = "out_trade_no")
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * 获取微信退款单号
     *
     * @return refund_id - 微信退款单号
     */
    public String getRefundId() {
        return refundId;
    }

    /**
     * 设置微信退款单号
     *
     * @param refundId 微信退款单号
     */
    @XmlElement(name = "refund_id")
    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    /**
     * 获取商户退款单号
     *
     * @return out_refund_no - 商户退款单号
     */
    public String getOutRefundNo() {
        return outRefundNo;
    }

    /**
     * 设置商户退款单号
     *
     * @param outRefundNo 商户退款单号
     */
    @XmlElement(name = "out_refund_no")
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
    }

    /**
     * 获取订单总金额，单位为分，只能为整数
     *
     * @return total_fee - 订单总金额，单位为分，只能为整数
     */

    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * 设置订单总金额，单位为分，只能为整数
     *
     * @param totalFee 订单总金额，单位为分，只能为整数
     */
    @XmlElement(name = "total_fee")
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     *
     * @return settlement_total_fee - 当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    /**
     * 设置当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     *
     * @param settlementTotalFee 当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XmlElement(name = "settlement_total_fee")
    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    /**
     * 获取退款总金额,单位为分
     *
     * @return refund_fee - 退款总金额,单位为分
     */
    public Integer getRefundFee() {
        return refundFee;
    }

    /**
     * 设置退款总金额,单位为分
     *
     * @param refundFee 退款总金额,单位为分
     */
    @XmlElement(name = "refund_fee")
    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    /**
     * 获取退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     *
     * @return settlement_refund_fee - 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    /**
     * 设置退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     *
     * @param settlementRefundFee 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XmlElement(name = "settlement_refund_fee")
    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    /**
     * 获取SUCCESS-退款成功
CHANGE-退款异常
REFUNDCLOSE—退款关闭
     *
     * @return refund_status - SUCCESS-退款成功
CHANGE-退款异常
REFUNDCLOSE—退款关闭
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * 设置SUCCESS-退款成功
CHANGE-退款异常
REFUNDCLOSE—退款关闭
     *
     * @param refundStatus SUCCESS-退款成功
CHANGE-退款异常
REFUNDCLOSE—退款关闭
     */
    @XmlElement(name = "refund_status")
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    /**
     * 获取资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     *
     * @return success_time - 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     */
    public String getSuccessTime() {
        return successTime;
    }

    /**
     * 设置资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     *
     * @param successTime 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     */
    @XmlElement(name = "success_time")
    public void setSuccessTime(String successTime) {
        this.successTime = successTime == null ? null : successTime.trim();
    }

    /**
     * 获取取当前退款单的退款入账方

1）退回银行卡：

{银行名称}{卡类型}{卡尾号}

2）退回支付用户零钱:

支付用户零钱

3）退还商户:

商户基本账户

商户结算银行账户

4）退回支付用户零钱通:

支付用户零钱通
     *
     * @return refund_recv_accout - 取当前退款单的退款入账方

1）退回银行卡：

{银行名称}{卡类型}{卡尾号}

2）退回支付用户零钱:

支付用户零钱

3）退还商户:

商户基本账户

商户结算银行账户

4）退回支付用户零钱通:

支付用户零钱通
     */

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    /**
     * 设置取当前退款单的退款入账方

1）退回银行卡：

{银行名称}{卡类型}{卡尾号}

2）退回支付用户零钱:

支付用户零钱

3）退还商户:

商户基本账户

商户结算银行账户

4）退回支付用户零钱通:

支付用户零钱通
     *
     * @param refundRecvAccout 取当前退款单的退款入账方

1）退回银行卡：

{银行名称}{卡类型}{卡尾号}

2）退回支付用户零钱:

支付用户零钱

3）退还商户:

商户基本账户

商户结算银行账户

4）退回支付用户零钱通:

支付用户零钱通
     */
    @XmlElement(name = "refund_recv_accout")
    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout == null ? null : refundRecvAccout.trim();
    }

    /**
     * 获取REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     *
     * @return refund_account - REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     */
    public String getRefundAccount() {
        return refundAccount;
    }

    /**
     * 设置REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     *
     * @param refundAccount REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     */
    @XmlElement(name = "refund_account")
    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount == null ? null : refundAccount.trim();
    }

    /**
     * 获取	
API接口
VENDOR_PLATFORM商户平台
     *
     * @return refund_request_source - 	
API接口
VENDOR_PLATFORM商户平台
     */

    public String getRefundRequestSource() {
        return refundRequestSource;
    }

    /**
     * 设置	
API接口
VENDOR_PLATFORM商户平台
     *
     * @param refundRequestSource 	
API接口
VENDOR_PLATFORM商户平台
     */
    @XmlElement(name = "refund_request_source")
    public void setRefundRequestSource(String refundRequestSource) {
        this.refundRequestSource = refundRequestSource == null ? null : refundRequestSource.trim();
    }


    /**
     * 获取加密信息请用商户秘钥进行解密，
     *
     * @return req_info - 加密信息请用商户秘钥进行解密，
     */
    public String getReqInfo() {
        return reqInfo;
    }

    /**
     * 设置加密信息请用商户秘钥进行解密，
     *
     * @param reqInfo 加密信息请用商户秘钥进行解密，
     */
    @XmlElement(name = "req_info")
    public void setReqInfo(String reqInfo) {
        this.reqInfo = reqInfo == null ? null : reqInfo.trim();
    }


    @Override
    public String toString() {
        return "WxRefundNotify{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", totalFee=" + totalFee +
                ", settlementTotalFee=" + settlementTotalFee +
                ", refundFee=" + refundFee +
                ", settlementRefundFee=" + settlementRefundFee +
                ", refundStatus='" + refundStatus + '\'' +
                ", successTime='" + successTime + '\'' +
                ", refundRecvAccout='" + refundRecvAccout + '\'' +
                ", refundAccount='" + refundAccount + '\'' +
                ", refundRequestSource='" + refundRequestSource + '\'' +
                ", reqInfo='" + reqInfo + '\'' +
                "} " + super.toString();
    }
}