package org.aj.wxpay.forest.convert;

import com.dtflys.forest.converter.xml.ForestJaxbConverter;
import com.dtflys.forest.exceptions.ForestConvertException;
import org.aj.wxpay.bean.base.WxBaseRequest;
import org.aj.wxpay.config.PayConfig;
import org.aj.wxpay.util.WxPayUtil;


/**
 * @autor aj
 * @description 自定义xml转换器适用于微信支付
 * @date 2022/1/2917:20
 */
public class CustomForestXmlConverter extends ForestJaxbConverter {

    private PayConfig payConfig;

    public CustomForestXmlConverter(PayConfig payConfig) {
        this.payConfig = payConfig;
    }

    @Override
    public String encodeToString(Object obj) {
        if(null != obj && obj instanceof WxBaseRequest){
            //填充appid 、mchid
            ((WxBaseRequest) obj ).setAppid(payConfig.getAppid());
            ((WxBaseRequest) obj ).setMchId(payConfig.getMchId());

            try {
                String signValue = WxPayUtil.getWxPaySign(obj,payConfig.getAppSecret());
                //设置签名
                ((WxBaseRequest) obj ).setSign(signValue);
            } catch (Exception e) {
                throw new ForestConvertException(this,e);
            }

        }
        return super.encodeToString(obj);

    }

}
