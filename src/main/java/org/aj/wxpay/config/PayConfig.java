package org.aj.wxpay.config;

import cn.hutool.core.util.StrUtil;
import org.aj.wxpay.constant.WxPayConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @autor aj
 * @description 支付的配置类须提前配置好
 * @date 2022/1/2911:44
 */
public class PayConfig {

    /**
     * 微信商户平台的密钥key用于加签跟验签
     */
    private String appSecret;
    /**
     * 应用id
     */
    private String appid;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 签名类型，默认为MD5
     */
    private String signType = WxPayConstants.MD5;
    /**
     * 是否开启沙箱默认为不开启
     */
    private boolean enableSandBox=false;

    /**
     * 微信需要证书认证的文件绝对地址
     * 目前仅支持证书格式为pkcs12格式
     * 若传入绝对地址 则下面的字节数组失效
     */
    private String sslFilePath;

    /**
     * 微信需要证书认证的证书文件 p12对应的字节数组
     * 目前仅支持证书格式为pkcs12格式
     *
     */
    private byte[] sslFileByte;

    /**
     * 此证书对应的密码 若不设置则默认为商户号
     */
    private String keystorePass;

    public PayConfig(String appSecret, String appid, String mchId,String sslFilePath,byte[] sslFileByte) {
        if(StrUtil.hasBlank(appSecret,appid,mchId)){
            throw new IllegalArgumentException("appSecret appid mchId  must not empty.");
        }
        if(StringUtils.isBlank(sslFilePath)  && (null == sslFileByte || sslFileByte.length == 0) ){
            throw new IllegalArgumentException("sslFilePath sslFileByte cannot be empty at the same time.");
        }
        this.appSecret = appSecret;
        this.appid = appid;
        this.mchId = mchId;
        this.keystorePass = mchId;
        this.sslFilePath = sslFilePath;
        this.sslFileByte = sslFileByte;
    }

    public boolean isEnableSandBox() {
        return enableSandBox;
    }

    public PayConfig setEnableSandBox(boolean enableSandBox) {
        this.enableSandBox = enableSandBox;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public PayConfig setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public PayConfig setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMchId() {
        return mchId;
    }

    public PayConfig setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getSignType() {
        return signType;
    }

    public PayConfig setSignType(String signType) {
        this.signType = signType;
        return this;
    }

    public String getSslFilePath() {
        return sslFilePath;
    }

    public PayConfig setSslFilePath(String sslFilePath) {
        this.sslFilePath = sslFilePath;
        return this;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    public PayConfig setKeystorePass(String keystorePass) {
        this.keystorePass = keystorePass;
        return this;
    }

    public byte[] getSslFileByte() {
        return sslFileByte;
    }

    public PayConfig setSslFileByte(byte[] sslFileByte) {
        this.sslFileByte = sslFileByte;
        return this;
    }
}
