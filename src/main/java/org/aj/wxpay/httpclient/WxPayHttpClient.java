package org.aj.wxpay.httpclient;

import com.dtflys.forest.annotation.Address;
import com.dtflys.forest.annotation.LogEnabled;
import com.dtflys.forest.annotation.PostRequest;
import com.dtflys.forest.annotation.XMLBody;

import org.aj.wxpay.bean.base.WxBaseRequest;
import org.aj.wxpay.bean.base.WxBaseResponse;
import org.aj.wxpay.bean.request.CreateWxPayLinkRequest;
import org.aj.wxpay.bean.request.QueryWxPayOrderRequest;
import org.aj.wxpay.bean.request.QueryWxRefundRequest;
import org.aj.wxpay.bean.request.WxRefundRequest;
import org.aj.wxpay.bean.response.GetSandboxSignKeyResponse;
import org.aj.wxpay.bean.response.QueryWxPayOrderResponse;
import org.aj.wxpay.bean.response.WxRefundResponse;
import org.aj.wxpay.bean.response.WxUnifiedOrderResponse;
import org.aj.wxpay.constant.WxPayConstants;

/**
 * @autor aj
 * @description 调用微信支付平台相关http客户端
 * @date 2022/1/2915:32
 */
@Address(scheme = "https",host = WxPayConstants.DOMAIN_API,port = "443")
public interface WxPayHttpClient {



    /**
     * @description 微信统一下单接口
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
     * @author aj
     * @date 2022/1/29 17:47
     * @param createWxPayLinkRequest
     * @return com.iotimc.yimi.pay.Bean.response.WxUnifiedOrderResponse
     */
    @LogEnabled(logRequest = true,logResponseContent = true,logResponseStatus = true)
    @PostRequest(url =WxPayConstants.UNIFIEDORDER_URL_SUFFIX)
    WxUnifiedOrderResponse unifiedOrder(@XMLBody CreateWxPayLinkRequest createWxPayLinkRequest);

    /**
     * @description 查询订单信息
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
     * @author aj
     * @date 2022/1/30 13:09
     * @param queryWxPayOrderRequestVo
     * @return com.iotimc.yimi.pay.Bean.response.QueryWxPayOrderResponse
     */
    @LogEnabled(logRequest = true,logResponseContent = true,logResponseStatus = true)
    @PostRequest(url =WxPayConstants.ORDERQUERY_URL_SUFFIX)
    QueryWxPayOrderResponse queryOrder(@XMLBody QueryWxPayOrderRequest queryWxPayOrderRequestVo);

    /**
     * @description 退款接口
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=6
     * @author aj
     * @date 2022/1/30 16:34
     * @param wxRefundRequest
     * @return com.iotimc.yimi.pay.Bean.response.WxRefundResponse
     */
    @LogEnabled(logRequest = true,logResponseContent = true,logResponseStatus = true)
    @PostRequest(url =WxPayConstants.REFUND_URL_SUFFIX,keyStore = "wxPaySsl")
    WxRefundResponse refund(@XMLBody WxRefundRequest wxRefundRequest);

    /**
     * @description 查询退款结果 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_5&index=7
     * @author aj
     * @date 2022/1/30 18:07
     * @param queryWxRefundRequest
     * @return java.lang.String
     */
    @LogEnabled(logRequest = true,logResponseContent = true,logResponseStatus = true)
    @PostRequest(url =WxPayConstants.REFUNDQUERY_URL_SUFFIX)
    String queryRefundOrder(@XMLBody QueryWxRefundRequest queryWxRefundRequest);

    /**
     * @description 关闭订单 防止重复支付
     * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3
     * @author aj
     * @date 2022/1/30 18:38
     * @param queryWxPayOrderRequest
     * @return com.iotimc.yimi.pay.Bean.base.WxBaseResponse
     */
    @LogEnabled(logRequest = true,logResponseContent = true,logResponseStatus = true)
    @PostRequest(url =WxPayConstants.CLOSEORDER_URL_SUFFIX)
    WxBaseResponse closeOrder(@XMLBody QueryWxPayOrderRequest queryWxPayOrderRequest);

    /**
     * @description 获取沙箱的签名
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=23_1&index=2
     * @author aj
     * @date 2022/1/30 22:48
     * @param WxBaseRequest
     * @return com.iotimc.yimi.pay.Bean.response.GetSandboxSignKeyResponse
     */
    @LogEnabled(logRequest = true,logResponseContent = true,logResponseStatus = true)
    @PostRequest(WxPayConstants.GET_SAND_BOX_KEY_SUFFIX)
    GetSandboxSignKeyResponse getSandboxSignKey(@XMLBody WxBaseRequest WxBaseRequest);

}
