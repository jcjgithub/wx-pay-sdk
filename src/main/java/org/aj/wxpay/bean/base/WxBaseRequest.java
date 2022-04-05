package org.aj.wxpay.bean.base;


import cn.hutool.core.util.RandomUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Description 调用微信支付各接口需要传递参数的基类
 * @Author aj 此处为基类故不要携带JAXB的注解@XmlRootElement(name="xml")
 * 关于注释例如 是	String(32) 表示此参数是必传参数，String(32)表示最多是32个的字符
 * @Date 2019/6/17 10:32
 **/
@XmlRootElement(name="xml")
public class WxBaseRequest implements Serializable {

    private static final long serialVersionUID = -3089827667631442895L;
    /**
     *随机字符串，不长于32位
     */

    private String nonceStr = RandomUtil.randomNumbers(10);
    /**
     *是	String(32)
     * 内部会处理不需要自动传入
     */
    private String sign;


    /**
     * 微信开放平台审核通过的应用APPID（请登录open.weixin.qq.com查看，注意与公众号的APPID不同）
     * 是	String(32)
     *
     */
    private String appid ;

    /**
     * 	微信支付分配的商户号
     * 	是	String(32)
     */
    private String mchId ;



    public String getNonceStr() {
        return nonceStr;
    }

    @XmlElement(name = "nonce_str")
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;

    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }
    @XmlElement(name = "mch_id")
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    @Override
    public String toString() {
        return "WxBaseRequest{" +
                "nonceStr='" + nonceStr + '\'' +
                ", sign='" + sign + '\'' +
                ", appid='" + appid + '\'' +
                ", mchId='" + mchId + '\'' +
                '}';
    }
}
