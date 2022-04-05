# wx-pay-sdk

## 介绍
### 序
目前微信官方还未提供sdk故自己封装一套。
使用者只需要调用对应的方法，无须自己手动加签、验签、封装为xml数据,提高生产效率
目前仅支持老版本的微信支付 ，即调用微信支付相关接口传入的还是xml数据格式
官网文档：https://pay.weixin.qq.com/wiki/doc/api/index.html
### 功能支持
1.微信下单，支持类型参考
org.aj.wxpay.constant.WxTradeType
2.查询订单状态  
3.关闭订单  
4.退款  
5.查询退款状态  

### 软件架构
软件架构说明  
 1.避免重复造轮子采用了开源的forest作为http客户端  
 2.工具类大部分采用了开源的hutool  
 3.实体类转换成xml数据采用的是JAXB 
 4.目前笔者使用的forest版本对微信退款的双向认证支持的不够友好，故退款接口
 直接使用的是apache httpclient进行封装，参考了微信给的demo
 
### 安装教程

####  引入maven依赖  
    &lt; groupId> org.aj&lt; /groupId>  
   &lt; artifactId>wx-pay-sdk&lt; /artifactId>  
    &lt; version>${wx-pay-sdk.version}&lt; /version>         
目前是1.0.0 



### 使用说明

#### 实例化配置类  
org.aj.wxpay.config.PayConfig   
org.aj.wxpay.config.HttpClientConfig（可选）
#### 实例化api类
org.aj.wxpay.api.WxPayApi
#### 调用api类里面的方法完成对应的业务需求即可

#### 以springboot举例子
##### 配置
@Configuration
public class WxPayConfig {


    /**
     * 商户秘钥
     */
    @Value("${fsPay.wx.pay.appSecret}")
    private String appSecret;
    /**
     * 应用id 或者是openid
     */
    @Value("${fsPay.wx.pay.appid}")
    private String appid;

    /**
     * 商户号
     */
    @Value("${fsPay.wx.pay.mchId}")
    private String mchId;


    /**
     * 证书文件 有些支付接口需要证书支持
     */
    @Value("${fsPay.wx.pay.sslFilePath}")
    private String sslFilePath;


    /**
     * 最大连接数,默认为200
     */
    @Value("${fsPay.wx.pay.httpConfig.maxConnections}")
    private Integer maxConnections=200;
    /**
     * 连接超时时间，默认为5秒
     */
    @Value("${fsPay.wx.pay.httpConfig.connectTimeout}")
    private Integer connectTimeout=5000;
    /**
     * 读取超时时间，默认为6秒
     */
    @Value("${fsPay.wx.pay.httpConfig.readTimeout}")
    private Integer readTimeout=6000;


    @Bean
    public PayConfig payConfig() throws IOException {




        Resource resource = new ClassPathResource(sslFilePath);
        byte[] sslFileByte = getBytes(resource.getInputStream());
        return new PayConfig(appSecret,appid,mchId,null,sslFileByte);
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

            byte[] bytes = new byte[8192];

            int length;

            while ((length = inputStream.read(bytes)) != -1){
                bos.write(bytes,0,length);
            }

            return bos.toByteArray();
        } finally {

            if(null != inputStream)
                inputStream.close();
        }
    }


    @Bean
    public HttpClientConfig wxHttpClientConfig(){
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        if(null != maxConnections)
            httpClientConfig.setMaxConnections(maxConnections);
        if(null != connectTimeout)
            httpClientConfig.setConnectTimeout(connectTimeout);
        if(null != readTimeout)
            httpClientConfig.setReadTimeout(readTimeout);
        return httpClientConfig;
    }

    @Bean
    public WxPayApi wxPayApi() throws IOException {
       return new WxPayApi(payConfig(),wxHttpClientConfig());
    }


###### 使用对应的方法
@RestController
public class WxPayController {

    @Autowired
    private WxPayApi wxPayApi;
    
    
    //...调用对应的方法即可
}
##### 关于处理微信的回调
1.当用户使用微信支付的用了代金券的时候，微信回调返回的参数是动态的因此不方便封装成实体类
因此在验签的时候还是需要拿原始数据，即不要使用org.aj.wxpay.bean.notify.WxPayNotify 进行验签

代码示例

    @RequestMapping(value = "app/wx/payNotify/fsMeal"
            //限制请求头 Content-Type 只处理类型为下面这几种
            ,consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.TEXT_XML_VALUE}
            //限制请求头 即Accept 类型须包含下面的才返回 org.springframework.web.servlet.mvc.condition.ProducesRequestCondition.getMatchingCondition
            //,produces = MediaType.APPLICATION_XML_VALUE
    )
    public NotifyResponse refundNotifyFsMeal(HttpServletRequest request) throws Exception {

        String originalData = WxPayUtil.readDataFromInputStream(request.getInputStream());
         //验签 目前WxPayNotify 类并没包含优惠券的字段故需要用原来的数据进行验签
            if(!WxPayUtil.validateSign(originalData,appSecret)){
                log.error("数据不符合要求，签名错误1001");
                return NotifyResponse.wrapError("数据不符合要求，签名错误1001");
            }
            //....省略业务逻辑
            return NotifyResponse.wrapSuccess();
    }
    
        @IgnoreInputLog
        @NoneAuthorizeExt
        @RequestMapping(value = "app/wx/RefundNotify/fsMeal"
                //限制请求头 Content-Type 只处理类型为下面这几种
                ,consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.TEXT_XML_VALUE}
                //限制请求头 即Accept 类型须包含下面的才返回 org.springframework.web.servlet.mvc.condition.ProducesRequestCondition.getMatchingCondition
                //,produces = MediaType.APPLICATION_XML_VALUE
        )
        public NotifyResponse payNotifyFsMeal(@RequestBody WxRefundNotify wxRefundNotify) throws Exception {
    
         if(!wxRefundNotify.isRequestSuccess()){
                    return NotifyResponse.wrapError("你返回一个错误结果给我，故不处理");
                }
         
          /**
           *进行解密 并把解密后的数据赋值给传入对象进行解密 并把解密后的数据赋值给传入对象
           * 这里需要注意目前笔者用的方法对JDK的版本有限制，在jdk1.8.0_241运行正常 
           * 在jdk1.8.0_77 运行报错 ，由于这不是本SDK的所负责的功能，若各位在使用过程中遇到报错可以自行百度
           * 或者使用你们自己的解密方法
           *
           */
          wxPayApi.decryptRefundNotify(wxRefundNotify.getReqInfo(),null,wxRefundNotify);
           //....省略业务逻辑
         return notifyResponse;
         }
#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
#### 鸣谢
1.感谢 forest、hutool的开源