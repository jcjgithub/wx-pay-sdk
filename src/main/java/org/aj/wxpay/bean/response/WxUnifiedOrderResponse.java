package org.aj.wxpay.bean.response;




import org.aj.wxpay.bean.base.WxBaseResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 接收调用统一下单接口返回的参数
 */
@XmlRootElement(name="xml")
public class WxUnifiedOrderResponse extends WxBaseResponse {

    /**
     * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，详细说明见
     */
    private String tradeType;

    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     * 这里不采用驼峰是因为为了兼容app支付
    */
    private String prepayId;

    /**
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     *
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可。时效性为2小时
     */
    private String codeUrl;


    public String getTradeType() {
        return tradeType;
    }
    @XmlElement(name = "trade_type")
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;

    }



    public String getPrepayId() {
        return prepayId;
    }
    @XmlElement(name = "prepay_id")
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;

    }

    public String getCodeUrl() {
        return codeUrl;
    }
    @XmlElement(name = "code_url")
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;

    }

    @Override
    public String toString() {
        return "WxUnifiedOrderResponse{" +
                "tradeType='" + tradeType + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                "} " + super.toString();
    }
}
