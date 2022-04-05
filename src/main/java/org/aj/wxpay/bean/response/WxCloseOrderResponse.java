package org.aj.wxpay.bean.response;



import org.aj.wxpay.bean.base.WxBaseResponse;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autor aj
 * @description 封装关闭订单的参数
 * @date 2022/1/3021:22
 */
@XmlRootElement(name = "xml")
public class WxCloseOrderResponse extends WxBaseResponse {

    @Override
    public String toString() {
        return "WxCloseOrderResponse{} " + super.toString();
    }
}
