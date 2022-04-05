package org.aj.wxpay.config;

/**
 * @autor aj
 * @description 发送请求的配置参数
 * @date 2022/1/2915:48
 */
public class HttpClientConfig {

    public enum ClientTypeEnum{
        okhttp3,httpclient;
    }
    /**
     * http客户端类型,默认为httpclient
     */
    private ClientTypeEnum clientTypeEnum= ClientTypeEnum.httpclient;
    /**
     * 最大连接数,默认为200
     */
    private Integer maxConnections=200;
    /**
     * 连接超时时间，默认为5秒
     */
    private Integer connectTimeout=5000;
    /**
     * 读取超时时间，默认为6秒
     */
    private Integer readTimeout=6000;

    public ClientTypeEnum getClientTypeEnum() {
        return clientTypeEnum;
    }

    public HttpClientConfig setClientTypeEnum(ClientTypeEnum clientTypeEnum) {
        this.clientTypeEnum = clientTypeEnum;
        return this;
    }

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public HttpClientConfig setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public HttpClientConfig setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public HttpClientConfig setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    @Override
    public String toString() {
        return "HttpClientConfig{" +
                "clientTypeEnum=" + clientTypeEnum +
                ", maxConnections=" + maxConnections +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                '}';
    }
}
