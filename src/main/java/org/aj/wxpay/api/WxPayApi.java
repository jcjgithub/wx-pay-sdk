package org.aj.wxpay.api;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.interceptor.Interceptor;
import com.dtflys.forest.interceptor.InterceptorFactory;

import org.aj.wxpay.bean.base.WxBaseRequest;
import org.aj.wxpay.bean.base.WxBaseResponse;
import org.aj.wxpay.bean.notify.WxRefundNotify;
import org.aj.wxpay.bean.request.*;
import org.aj.wxpay.bean.response.*;
import org.aj.wxpay.config.HttpClientConfig;
import org.aj.wxpay.config.PayConfig;
import org.aj.wxpay.constant.WxPayConstants;
import org.aj.wxpay.constant.WxPayStatusEnum;
import org.aj.wxpay.exception.ApiException;
import org.aj.wxpay.forest.convert.CustomForestXmlConverter;
import org.aj.wxpay.forest.interceptor.CustomInterceptor;
import org.aj.wxpay.httpclient.WxPayHttpClient;
import org.aj.wxpay.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


/**
 * @Description 处理微信支付相关的接口
 * 调用者不需要设置入参的 appid mch_id nonceStr sign 这些参数 方法内部会自行处理
 *
 * @Author aj
 * @Date 2019/6/14 16:06
 **/
public class WxPayApi {

    private static final Logger log = LoggerFactory.getLogger(WxPayApi.class);



    private PayConfig payConfig;

    private HttpClientConfig httpClientConfig;

    private WxPayHttpClient wxPayHttpClient;

    /**
     * 沙箱的验签key
     */
    private String sandBoxKey;

    public WxPayApi(PayConfig payConfig) {
        this.payConfig = payConfig;
        this.wxPayHttpClient= createWxPayHttpClient(null);
    }

    public WxPayApi(PayConfig payConfig, HttpClientConfig httpClientConfig) {
        this.payConfig = payConfig;
        if(null == httpClientConfig)
            httpClientConfig = new HttpClientConfig();
        this.httpClientConfig = httpClientConfig;
        //实例化forest-http客户端
        this.wxPayHttpClient = createWxPayHttpClient(httpClientConfig);

        //若开启了沙箱则拿到沙箱的密钥替换掉正式的密钥
        if(payConfig.isEnableSandBox()){
            try {
                WxBaseRequest wxBaseRequest = new WxBaseRequest();
                GetSandboxSignKeyResponse sandboxSignKey = wxPayHttpClient.getSandboxSignKey(wxBaseRequest);
                if(StringUtils.isNotBlank(sandboxSignKey.getSandboxSignkey())){
                    payConfig.setAppSecret(sandboxSignKey.getSandboxSignkey());
                    this.sandBoxKey = sandboxSignKey.getSandboxSignkey();
                }
            } catch (Exception e){
                log.error("初始化获取沙箱密钥的失败，将在调用支付相关的接口都进行尝试获取\n",e);

            }
        }

    }

    public PayConfig getPayConfig() {
        return payConfig;
    }



    private WxPayHttpClient createWxPayHttpClient(HttpClientConfig httpClientConfig) {
        ForestConfiguration configuration = ForestConfiguration.createConfiguration();
        configuration.setXmlConverter(new CustomForestXmlConverter(payConfig));
        List<Class<? extends Interceptor>> interceptors = configuration.getInterceptors();
        if(null == interceptors)
            interceptors = new ArrayList<>();
        interceptors.add(CustomInterceptor.class);
        configuration.setInterceptors(interceptors);
        InterceptorFactory interceptorFactory = configuration.getInterceptorFactory();
        CustomInterceptor interceptor = interceptorFactory.getInterceptor(CustomInterceptor.class);
        interceptor.setPayConfig(payConfig);



        //若有则进行配置否则用默认值
        if(null != httpClientConfig.getClientTypeEnum()){
            configuration.setBackendName(httpClientConfig.getClientTypeEnum().toString());
        }
        if(null != httpClientConfig.getMaxConnections()){
            configuration.setMaxConnections(httpClientConfig.getMaxConnections());
        }
        if(null != httpClientConfig.getConnectTimeout()){
            configuration.setConnectTimeout(httpClientConfig.getConnectTimeout());
        }
        if(null != httpClientConfig.getReadTimeout()){
            configuration.setMaxConnections(httpClientConfig.getMaxConnections());
        }
        WxPayHttpClient instance = configuration.createInstance(WxPayHttpClient.class);
        return instance;
    }

    public HttpClientConfig getHttpClientConfig() {
        return httpClientConfig;
    }

    public WxPayHttpClient getWxPayHttpClient() {
        return wxPayHttpClient;
    }

    /**
     * https://pay.weixin.qq.com/wiki/doc/api/index.html
     * @description 统一下单接口 适用于 com.iotimc.yimi.pay.constant.WxPayConstants 类型
     * @author aj
     * @date 2022/1/30 12:12
     * @param createWxPayLinkRequest
     * @return org.aj.wxpay.bean.response.WxUnifiedOrderResponse
     */
    public WxUnifiedOrderResponse unifiedOrder(CreateWxPayLinkRequestVo createWxPayLinkRequest)  {

        //若是沙箱环境则获取下沙箱的签名密钥在有必要的情况
        setSandBoxSinKeyWhenInitFail();


        CreateWxPayLinkRequest target = new CreateWxPayLinkRequest();
        target.setSignType(payConfig.getSignType());
        BeanUtil.copyProperties(createWxPayLinkRequest, target);

        //调用统一下单接口
        return wxPayHttpClient.unifiedOrder(target);


    }
    /**
     * @description 在初始化的时候获取沙箱的密钥失败的情况下在次进行尝试
     * 若获取不到则抛出业务性异常
     * @author aj
     * @date 2022/1/30 23:06
     * @return void
     */
    private void setSandBoxSinKeyWhenInitFail() {
        if(payConfig.isEnableSandBox() && StringUtils.isBlank(sandBoxKey)){
            WxBaseRequest wxBaseRequest = new WxBaseRequest();
            GetSandboxSignKeyResponse sandboxSignKey = wxPayHttpClient.getSandboxSignKey(wxBaseRequest);
            if(!sandboxSignKey.isRequestSuccess()){
                throw new ApiException("获取沙箱密钥失败无法进行下一步,"+sandboxSignKey.getReturnMsg());
            }
            if(!sandboxSignKey.isBusinessSuccess()){
                throw new ApiException(sandboxSignKey.getErrCode(),"获取沙箱密钥失败无法进行下一步,"+sandboxSignKey.getErrCodeDes());
            }
            payConfig.setAppSecret(sandboxSignKey.getSandboxSignkey());
        }
    }

    private String getPrefix() {
        String prefix = "";
        if(payConfig.isEnableSandBox()){
            prefix = WxPayConstants.SAND_BOX_SUFFIX;
        }
        return prefix;
    }

    private void validateParam( Object paramObj) {
        if(null == paramObj){
            throw new IllegalArgumentException("arg must not null");
        }
    }

    private void fillCommonAttribute(WxBaseRequest baseRequest){
        //填充appid 、mchid
        baseRequest.setAppid(payConfig.getAppid());
        baseRequest.setMchId(payConfig.getMchId());

        String signValue = WxPayUtil.getWxPaySign(baseRequest,payConfig.getAppSecret());
        //设置签名
        baseRequest.setSign(signValue);
    }




    /**
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2
     * @description 创建app支付下单需要的参数 以供app拉起微信支付
     * 若业务异常会抛出 com.iotimc.yimi.base.bean.exception.BusException
     * @author aj
     * @date 2022/1/30 12:58
     * @param createWxPayLinkRequest
     * @return com.iotimc.yimi.pay.Bean.response.CreateWxPayAppLinkResponse
     */
    public CreateWxPayAppLinkResponse createWxAppPayLink(CreateWxPayLinkRequestVo createWxPayLinkRequest){
        //调用统一下单接口得到会话标识
        WxUnifiedOrderResponse wxUnifiedOrderResponseVo = unifiedOrder(createWxPayLinkRequest);
        if(!wxUnifiedOrderResponseVo.isRequestSuccess()){
            throw new ApiException(wxUnifiedOrderResponseVo.getReturnMsg());
        }
        if(!wxUnifiedOrderResponseVo.isBusinessSuccess()){
            throw new ApiException(wxUnifiedOrderResponseVo.getErrCode(),wxUnifiedOrderResponseVo.getErrCodeDes());
        }


        //组装需要返回的数据
        CreateWxPayAppLinkResponse createWxPayAppLinkResponseVo = new CreateWxPayAppLinkResponse(
                wxUnifiedOrderResponseVo.getAppid(), wxUnifiedOrderResponseVo.getMchId(),
                wxUnifiedOrderResponseVo.getPrepayId(),  RandomUtil.randomNumbers(10),
                System.currentTimeMillis() / 1000 + "", ""
        );
        //对返回的数据进行签名
        TreeMap<String, Object> nameAndValue = WxPayUtil.getPropertyNameAndValue(createWxPayAppLinkResponseVo);
        //移除掉key为sign的值，因为下面的拼接参数用不到
        if (nameAndValue.containsKey("sign"))
            nameAndValue.remove("sign");
        if (nameAndValue.containsKey("package_ext") ) {
            nameAndValue.put("package", nameAndValue.get("package_ext"));
            nameAndValue.remove("package_ext");
        }

        if (nameAndValue.containsKey("packageExt")) {
            nameAndValue.put("package", nameAndValue.get("packageExt"));
            nameAndValue.remove("packageExt");
        }

        createWxPayAppLinkResponseVo.setSign(WxPayUtil.getWxPaySign(nameAndValue,payConfig.getAppSecret()));

        return createWxPayAppLinkResponseVo;


    }


    /**
     * @description 查询订单
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
     * @author aj
     * @date 2022/1/30 13:01
     * @param transactionId 微信订单号，即由微信系统内部生成的订单号  二者传其一
     * @param outTradeNo  商户订单号，我们自己的订单号
     * @return com.iotimc.yimi.pay.Bean.response.QueryWxPayOrderResponse
     */
    public QueryWxPayOrderResponse queryOrder(String transactionId,
                                              String outTradeNo) {

        if(StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)){
            throw new IllegalArgumentException("transactionId outTradeNo must not all be empty.");
        }
        //若是沙箱环境则获取下沙箱的签名密钥在有必要的情况
        setSandBoxSinKeyWhenInitFail();
        QueryWxPayOrderRequest queryWxPayOrderRequestVo = new QueryWxPayOrderRequest(transactionId,outTradeNo);


         return wxPayHttpClient.queryOrder(queryWxPayOrderRequestVo);

    }
    /**
     * @description 判断订单是否存在
     * @author aj
     * @date 2022/1/30 16:39
     * @param outTradeNo  商户创建的订单号 二选一
     * @param transactionId 微信生成的订单号，在支付通知中有返回
     * @return boolean true为存在
     */
    public boolean isExits(String outTradeNo,
                           String transactionId){
        QueryWxPayOrderResponse queryWxPayOrderResponse = queryOrder(transactionId, outTradeNo);
        if(!queryWxPayOrderResponse.isRequestSuccess()){
            throw new ApiException(queryWxPayOrderResponse.getReturnMsg());
        }

        if(!queryWxPayOrderResponse.isBusinessSuccess()){
           if("ORDERNOTEXIST".equalsIgnoreCase(queryWxPayOrderResponse.getErrCode())){
               return false;
           }
            throw new ApiException(queryWxPayOrderResponse.getErrCode(),queryWxPayOrderResponse.getErrCodeDes());
        }
        return true;
    }





    /**
     * @description 查询订单的状态是否为已关闭
     * @author aj
     * @date 2022/1/30 16:41
     * @param outTradeNo  商户创建的订单号  二选一
     * @param transactionId 微信生成的订单号，在支付通知中有返回
     * @return boolean
     */
    public boolean isPayTradeClose(String transactionId,
                                   String outTradeNo)  {
        QueryWxPayOrderResponse queryWxPayOrderResponse = queryOrder(transactionId, outTradeNo);

        if(WxPayStatusEnum.CLOSED.toString().equalsIgnoreCase(queryWxPayOrderResponse.getTradeState()))
            return true;
        return false;
    }




    /**
     * @description 申请退款
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=6
     * @author aj
     * @date 2022/1/30 16:39
     * @param wxRefundRequest
     * @return com.iotimc.yimi.pay.Bean.response.WxRefundResponse
     */
/*    public WxRefundResponse refund(WxRefundRequest wxRefundRequest) {

        //校验必传的参数是否传入
        validateParam(wxRefundRequest);
        //若是沙箱环境则获取下沙箱的签名密钥在有必要的情况
        setSandBoxSinKeyWhenInitFail();
        wxRefundRequest.setSignType(payConfig.getSignType());
        //填充通用属性
        fillCommonAttribute(wxRefundRequest);


        return wxPayHttpClient.refund(getPrefix(),wxRefundRequest);
    }*/

    /**
     * @description 直接使用apache httpclient 完成申请退款
     * @author aj
     * @date 2022/1/30 17:39
     * @param wxRefundRequest
     * @return com.iotimc.yimi.pay.Bean.response.WxRefundResponse
     */
    public WxRefundResponse refundUseNative(WxRefundRequestVo wxRefundRequest){


        //若是沙箱环境则获取下沙箱的签名密钥在有必要的情况
        setSandBoxSinKeyWhenInitFail();

        WxRefundRequest target = new WxRefundRequest();
        BeanUtil.copyProperties(wxRefundRequest, target);
        target.setSignType(payConfig.getSignType());

        ClientCustomSSL clientCustomSSL = new ClientCustomSSL(payConfig,httpClientConfig);
        String url = "https://"+ WxPayConstants.DOMAIN_API+getPrefix()+WxPayConstants.REFUND_URL_SUFFIX;

        String propertyNameAndValueToXml = XmlEntityUtil.getPropertyNameAndValueToXml(target,true);
        //log.info("xml:\n"+propertyNameAndValueToXml);
        String res = clientCustomSSL.doRefund(url, propertyNameAndValueToXml);

        return XmlEntityUtil.packageXmlDataToEntity(res,WxRefundResponse.class);

    }



    /**
     * @description 查询退款信息
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_5&index=7
     * @author aj
     * @date 2022/1/30 18:24
     * @param queryWxRefundRequest 退款状态可以根据 com.iotimc.yimi.pay.constant.WxRefundStatusEnum 进行查看
     * @return com.iotimc.yimi.pay.Bean.response.QueryWxRefundOrderResponse
     */
    public QueryWxRefundOrderResponse queryRefundOrder(QueryWxRefundRequestVo queryWxRefundRequest)  {

        if(null == queryWxRefundRequest){
            throw new IllegalArgumentException("queryWxRefundRequest must not null");
        }
        //若是沙箱环境则获取下沙箱的签名密钥在有必要的情况
        setSandBoxSinKeyWhenInitFail();

        QueryWxRefundRequest target = new QueryWxRefundRequest(queryWxRefundRequest);

        String res = wxPayHttpClient.queryRefundOrder(target);

        QueryWxRefundOrderResponse queryWxRefundOrderResponse = XmlEntityUtil.packageXmlDataToEntity(res, QueryWxRefundOrderResponse.class);
        queryWxRefundOrderResponse.setBody(res);

        //设置退款状态
        Integer refundCount = queryWxRefundOrderResponse.getRefundCount();
        if(queryWxRefundOrderResponse.isBusinessSuccess()){
            queryWxRefundOrderResponse.setCurrentRefundStatus(XmlEntityUtil.getXmlTagValue(res,"refund_status_"+(refundCount -1)));

            //设置退款时间
            if(WxPayStatusEnum.SUCCESS.toString().equalsIgnoreCase(queryWxRefundOrderResponse.getCurrentRefundStatus())){
                queryWxRefundOrderResponse.setCurrentRefundTime(XmlEntityUtil.getXmlTagValue(res,"refund_success_time_"+(refundCount -1)));
            }
            //设置退款入账方信息
            queryWxRefundOrderResponse.setCurrentRefundRecdAccount(XmlEntityUtil.getXmlTagValue(res,"refund_recv_accout_"+(refundCount -1)));
        }

        return queryWxRefundOrderResponse;

    }


    /**
     * @description 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
     * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_3&index=5
     * @author aj
     * @date 2022/1/30 18:37
     * @param outTradeNo 商户的订单号
     * @return com.iotimc.yimi.pay.Bean.base.WxBaseResponse
     */
    public WxBaseResponse closeOrder(String  outTradeNo) {

       if(StringUtils.isBlank(outTradeNo)){
           throw new IllegalArgumentException("outTradeNo must not null");
       }
        //若是沙箱环境则获取下沙箱的签名密钥在有必要的情况
        setSandBoxSinKeyWhenInitFail();
       QueryWxPayOrderRequest queryWxPayOrderRequest = QueryWxPayOrderRequest.buildByOutTradNo(outTradeNo);


       return   wxPayHttpClient.closeOrder(queryWxPayOrderRequest);

    }


    /**
     * @description 解密 微信给的密感数据并把解密后的数据封装到实体类里面
     * @author aj
     * @date 2022/2/12 15:14
     * @param reqInfo 微信给的密感数据
     * @param charsetName 字符集
     * @param wxRefundNotify 退款对象 用来接收密感数据 若为null则不进行接收
     * @return com.iotimc.yimi.pay.Bean.notify.WxRefundNotify 若参数wxRefundNotify 为null 则返回一个新对象 否则返回 wxRefundNotify
     * 对象 并包含了解密后的数据
     */
    public WxRefundNotify decryptRefundNotify(String reqInfo, String charsetName, WxRefundNotify wxRefundNotify) throws Exception {


        WxRefundNotify decryptRefund = decryptRefundNotify(reqInfo,charsetName,WxRefundNotify.class);

        //把原来的属性进行填充
        if(null != wxRefundNotify)
         BeanUtil.copyProperties(decryptRefund,wxRefundNotify,new CopyOptions().setIgnoreNullValue(true));

        return null == wxRefundNotify ? decryptRefund : wxRefundNotify;
    }

    /**
     * @description 解密 微信给的密感数据并把解密后的数据封装到实体类里面
     * @author aj
     * @date 2022/2/12 15:14
     * @param reqInfo 微信给的密感数据
     * @param charsetName 字符集
     * @param clazz 需要封装的字节码对象
     * @return com.iotimc.yimi.pay.Bean.notify.WxRefundNotify
     */
    public <T>T decryptRefundNotify(String reqInfo,String charsetName,Class<T> clazz) throws Exception {

        if(StringUtils.isBlank(reqInfo)){
            throw new IllegalArgumentException("reqInfo must not empty");
        }
        //开始进行解密
        WxPayAESUtil wxPayAESUtil = new WxPayAESUtil(payConfig.getAppSecret(),charsetName);

        String decryptData = wxPayAESUtil.decryptData(reqInfo);

        return XmlEntityUtil.packageXmlDataToEntity(decryptData, clazz);

    }



    public static void main(String[] args) throws Exception {
      /*  WxPayApi wxPayApi = new WxPayApi(new PayConfig());

        wxPayApi.unifiedOrder(new CreateWxPayLinkRequest());*/

        String s = StrChangeUtil.humpToLine("appid");
        System.out.println(s);
    }














}
