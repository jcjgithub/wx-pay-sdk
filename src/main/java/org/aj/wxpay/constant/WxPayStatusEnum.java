package org.aj.wxpay.constant;

/**
 * @autor aj
 * @description 微信支付状态
 * @date 2022/1/3013:45
 */
public enum WxPayStatusEnum {

/**
 * SUCCESS—支付成功
 *
 * REFUND—转入退款
 *
 * NOTPAY—未支付
 *
 * CLOSED—已关闭
 *
 * REVOKED—已撤销（付款码支付）
 *
 * USERPAYING--用户支付中（付款码支付）
 *
 * PAYERROR--支付失败(其他原因，如银行返回失败)
 * 支付状态机请见下单API页面
 * ACCEPT--已接收，等待扣款
 *
 */
 SUCCESS,REFUND,CLOSED,REVOKE,USERPAYING,ACCEPT,PAYERROR;
}
