package org.aj.wxpay.util;


import org.aj.wxpay.config.HttpClientConfig;
import org.aj.wxpay.config.PayConfig;
import org.aj.wxpay.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.security.SecureRandom;


/**
 * 摘自微信支付提供的demo
 *
 * context.
 */
public class ClientCustomSSL {

    private static final Logger log = LoggerFactory.getLogger(ClientCustomSSL.class);
    private PayConfig payConfig;

    private HttpClientConfig httpClientConfig;

    public ClientCustomSSL(PayConfig payConfig, HttpClientConfig httpClientConfig) {
        this.payConfig = payConfig;
        this.httpClientConfig = httpClientConfig;
    }

    public  String doRefund(String url, String data)  {

        BasicHttpClientConnectionManager connManager;

        InputStream inputStream = null;

        try {
            if(StringUtils.isNotBlank(payConfig.getSslFilePath())){

                /**
                 * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
                 */
                inputStream = new FileInputStream(new File(payConfig.getSslFilePath()));//P12文件在服务器磁盘中的目录
            }else{
                inputStream = new ByteArrayInputStream(payConfig.getSslFileByte());
            }

            KeyStore ks  = KeyStore.getInstance("PKCS12");


            ks.load(inputStream,payConfig.getKeystorePass().toCharArray());//这里写密码..默认是你的MCHID


            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, payConfig.getKeystorePass().toCharArray());
            // Trust own CA and all self-signed certs
            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslConnectionSocketFactory)
                            .build(),
                    null,
                    null,
                    null
            );
            HttpClient httpClient = HttpClientBuilder.create()
                    .setConnectionManager(connManager)
                    .build();


            HttpPost httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(httpClientConfig.getReadTimeout() == null ? 6*1000 : httpClientConfig.getReadTimeout())
                    .setConnectTimeout(httpClientConfig.getConnectTimeout() == null ? 8*1000 : httpClientConfig.getReadTimeout())
                    .build();
            httpPost.setConfig(requestConfig);

            StringEntity postEntity = new StringEntity(data, "UTF-8");
            httpPost.addHeader("Content-Type", "application/xml");
            //httpPost.addHeader("User-Agent", USER_AGENT + " " + config.getMchID());
            httpPost.setEntity(postEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            return EntityUtils.toString(httpEntity, "UTF-8");
        }catch (Exception e){
            throw new ApiException("refund error",e);
        } finally {
            if(null != inputStream && inputStream instanceof FileInputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
    }

}
