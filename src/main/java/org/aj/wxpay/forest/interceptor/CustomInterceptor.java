package org.aj.wxpay.forest.interceptor;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import com.dtflys.forest.reflection.ForestMethod;
import org.aj.wxpay.bean.base.WxBaseResponse;
import org.aj.wxpay.config.PayConfig;
import org.aj.wxpay.constant.WxPayConstants;


import java.net.URI;

/**
 * @autor aj
 * @description 自定义拦截器在发送请求前对请求参数进行统一的修复目前是进行加签操作
 * @date 2022/1/2920:39
 */
public class CustomInterceptor implements Interceptor<WxBaseResponse> {

    private PayConfig payConfig;

    public CustomInterceptor(PayConfig payConfig) {
        this.payConfig = payConfig;
    }

    public PayConfig getPayConfig() {
        return payConfig;
    }

    public CustomInterceptor() {
    }

    public CustomInterceptor setPayConfig(PayConfig payConfig) {
        this.payConfig = payConfig;
        return this;
    }


    public void onInvokeMethod(ForestRequest request, ForestMethod method, Object[] args) {

        if(payConfig.isEnableSandBox()){ //若开启了沙盒则把url标识为沙盒
            URI uri = request.getURI();
            if(!uri.getPath().startsWith(WxPayConstants.SAND_BOX_SUFFIX)){
                request.setUrl( WxPayConstants.SAND_BOX_SUFFIX+uri.getPath());
            }

        }
    }
}
