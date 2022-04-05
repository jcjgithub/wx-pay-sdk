package org.aj.wxpay.bean.response;



import org.aj.wxpay.bean.base.WxBaseResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autor aj
 * @description 用于封装拿到的沙盒验签的密钥
 * @date 2022/1/3022:45
 */
@XmlRootElement(name="xml")
public class GetSandboxSignKeyResponse extends WxBaseResponse {

    /**
     * 沙箱密钥 	sandbox_signkey 	否 	013467007045764 	String(32) 	返回的沙箱密钥
     */
    private String sandboxSignkey;

    public String getSandboxSignkey() {
        return sandboxSignkey;
    }

    @XmlElement(name = "sandbox_signkey")
    public void setSandboxSignkey(String sandboxSignkey) {
        this.sandboxSignkey = sandboxSignkey;
    }

    @Override
    public String toString() {
        return "GetSandboxSignKeyResponse{" +
                "sandboxSignkey='" + sandboxSignkey + '\'' +
                "} " + super.toString();
    }
}
