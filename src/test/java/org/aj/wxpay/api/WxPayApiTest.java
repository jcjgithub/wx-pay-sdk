package org.aj.wxpay.api;

import cn.hutool.core.util.IdUtil;

import org.aj.wxpay.bean.base.WxBaseResponse;
import org.aj.wxpay.bean.request.CreateWxPayLinkRequestVo;
import org.aj.wxpay.bean.request.QueryWxRefundRequestVo;
import org.aj.wxpay.bean.request.WxRefundRequestVo;
import org.aj.wxpay.bean.response.*;
import org.aj.wxpay.config.HttpClientConfig;
import org.aj.wxpay.config.PayConfig;
import org.junit.Before;
import org.junit.Test;



public class WxPayApiTest {

    private WxPayApi wxPayApi;

    @Before
    public void init(){
        //apiclient_cert.p12
        PayConfig payConfig = new PayConfig("appSecret","appid"
                ,"mchId","sslFilepath",null);
        payConfig.setEnableSandBox(false);


        wxPayApi = new WxPayApi(payConfig,new HttpClientConfig());
        //   //订单号：61f688e53b50adc35879af11
    }


    @Test
    public void unifiedOrder()  {

        String outTradeNo = IdUtil.objectId();
        CreateWxPayLinkRequestVo createWxPayLinkRequest = new CreateWxPayLinkRequestVo(
                "月套餐"
                ,outTradeNo
                ,100
                ,"117.183.32.99"
                ,"20220130123223"
                ,"https://localhost:8088/module/wx/notify"
        );
        WxUnifiedOrderResponse wxUnifiedOrderResponse = wxPayApi.unifiedOrder(createWxPayLinkRequest);

        System.out.println(wxUnifiedOrderResponse);
    }

    @Test
    public void createWxAppPayLink(){
        String outTradeNo = IdUtil.objectId();
        CreateWxPayLinkRequestVo createWxPayLinkRequest = new CreateWxPayLinkRequestVo(
                "月套餐"
                ,outTradeNo
                ,100
                ,"117.183.32.99"
                ,"20220130123223"
                ,"https://localhost:8088/module/wx/notify"
        );
        CreateWxPayAppLinkResponse wxAppPayLink = wxPayApi.createWxAppPayLink(createWxPayLinkRequest);

        System.out.println(wxAppPayLink);

    }

    @Test
    public void queryOrder(){

        QueryWxPayOrderResponse queryWxPayOrderResponse = wxPayApi.queryOrder(null,  IdUtil.objectId());

        System.out.println(queryWxPayOrderResponse);
    }

    @Test
    public void isExits()  {

        boolean exits = wxPayApi.isExits(null,  IdUtil.objectId());
        System.out.println(exits);
    }

    @Test
    public void isPayTradeClose() throws Exception {

        boolean payTradeClose = wxPayApi.isPayTradeClose(null,  IdUtil.objectId());
        System.out.println(payTradeClose);
    }

    @Test
    public void refund() {

        String outRefundNo = IdUtil.objectId();
        WxRefundRequestVo wxRefundRequest = new WxRefundRequestVo(
                null
                ,"61f690a53b507ab32d2de931"
                , outRefundNo
                ,100
                ,100,
                "退款"
                ,"aa"
        );
        WxRefundResponse refund = wxPayApi.refundUseNative(wxRefundRequest);
        System.out.println(refund);
    }

    @Test
    public void refundUseNative()  {

        String outRefundNo = IdUtil.objectId();
        WxRefundRequestVo wxRefundRequest = new WxRefundRequestVo(
                null
                ,"61f690a53b507ab32d2de931"
                ,outRefundNo
                ,100
                ,100,
                "退款"
                ,"aa"
        );
        WxRefundResponse refund = wxPayApi.refundUseNative(wxRefundRequest);
        System.out.println(refund);

    }

    @Test
    public void queryRefundOrder(){



        QueryWxRefundOrderResponse queryWxRefundOrderResponse = wxPayApi.queryRefundOrder( QueryWxRefundRequestVo.buildByOutRefundNo( IdUtil.objectId()));
        System.out.println(queryWxRefundOrderResponse);
    }

    @Test
    public void closeOrder()  {

        WxBaseResponse wxBaseResponse = wxPayApi.closeOrder("61f688e53b50adc35879af11");
        System.out.println(wxBaseResponse);
    }
}