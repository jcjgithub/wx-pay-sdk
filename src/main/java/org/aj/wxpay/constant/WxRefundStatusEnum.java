package org.aj.wxpay.constant;

/**
 * @autor aj
 * @description 微信退款状态
 * @date 2022/1/3018:18
 */
public enum  WxRefundStatusEnum {
    /**
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
    SUCCESS,REFUNDCLOSE,PROCESSING,CHANGE;
}
