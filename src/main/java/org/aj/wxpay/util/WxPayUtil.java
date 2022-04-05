package org.aj.wxpay.util;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.aj.wxpay.exception.ValidateSignException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * @description 封装微信支付相关用到的工具类
 * @author aj
 * @date 2022/1/29 10:34
 */
public class WxPayUtil {

    private static final Logger log = LoggerFactory.getLogger(WxPayUtil.class);

    /**
     * 获得微信支付签名
     *
     * @param object 需要签名的对象
     * @param payKey 支付的密钥key 微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     * @return
     */
    public static String getWxPaySign(Object object, String payKey)  {
        /**
         * 1.第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），
         * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
         */
        TreeMap<String, Object> propertyNameAndValue;
        if (object instanceof Map) {
            propertyNameAndValue = (TreeMap<String, Object>) object;
        } else {
            propertyNameAndValue = getPropertyNameAndValue(object);
        }
        //移除掉key为sign的值，因为下面的拼接参数用不到
        if (propertyNameAndValue.containsKey("sign"))
            propertyNameAndValue.remove("sign");
        //拼接字符串
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> stringObjectEntry : propertyNameAndValue.entrySet()) {
            String key = stringObjectEntry.getKey();
            //若key是驼峰则转换成下划线
            key = StrChangeUtil.humpToLine(key);

            sb.append(key + "=" + stringObjectEntry.getValue() + "&");
        }
        /**
         * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，
         * 得到sign值signValue。
         */
        String str = sb.toString() + "key=" + payKey;
        if(log.isDebugEnabled()){
            log.debug("拼接的结果{{}}",str);
        }
        return DigestUtil.md5Hex(str).toUpperCase();
    }


    /**
     * 利用反射机制把是实体类转换成map,比按照属性名称的字典排序升序，忽略掉为空和为null的
     *
     * @param object
     * @return
     */
    public static TreeMap<String, Object> getPropertyNameAndValue(Object object)  {

        Map<String, Object> beanToMap = BeanUtil.beanToMap(object, true, true);
        //移除掉空字符串
        TreeMap<String,Object> treeMap = new TreeMap<>();

        for (Map.Entry<String, Object> entry : beanToMap.entrySet()) {
            Object value = entry.getValue();

            if(value instanceof String && StringUtils.isBlank((CharSequence) value)){
                continue;
            }
            treeMap.put(entry.getKey(),value);
        }


       return treeMap;
    }



    /**
     * @description 从请求流里面读取数据
     * @author aj
     * @date 2022/2/9 12:34
     * @param inputStream request.getInputStream 得到的对象
     * @return java.lang.String
     */
    public static String readDataFromInputStream(InputStream inputStream) throws IOException {

        return readDataFromInputStream(inputStream,"UTF-8") ;
    }

    /**
     * @description 从请求流里面读取数据
     * @author aj
     * @date 2022/2/9 12:34
     * @param inputStream request.getInputStream 得到的对象
     * @param charset 默认为UTF-8
     * @return java.lang.String
     */
    public static String readDataFromInputStream(InputStream inputStream,String charset) throws IOException {
        if(null == inputStream){
            throw new IllegalArgumentException("inputStream must not null");
        }
        if(StringUtils.isBlank(charset)){
            charset = "UTF-8";
        }
        try {
            int len;

            // 用于缓存每次读取的数据
            byte[] buffer = new byte[8192];

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            while ((len = inputStream.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            return bos.toString(charset);
        } finally {

            if(null != inputStream ){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }

    }


    /**
     * @description 验签
     * @author aj
     * @date 2022/1/29 11:06
     * @param xmlStr 需要校验的xml数据 非空 否则抛出参数异常的错误
     * @param appSecret 用来验签的密钥 非空 否则抛出参数异常的错误
     * @return boolean
     */
    public static boolean validateSign(String  xmlStr,String appSecret)  {
        if(StringUtils.isBlank(xmlStr) || StringUtils.isBlank(appSecret)){
            throw new IllegalArgumentException("xmlstr appSecret must not empty.");
        }
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            throw new ValidateSignException("validate sign parse xml error",e);
        }
        Element rootElement = document.getRootElement();

        //进行验签操作
        Map<String, Object> map = new TreeMap<>();
        //对某节点下的所有子节点进行遍历.
        for (Iterator it = rootElement.elementIterator(); it.hasNext(); ) {
            Element element = (Element) it.next();
            if ("sign".equalsIgnoreCase(element.getName().trim()))
                continue;
            map.put(element.getName(), element.getText());
        }

        String wxPaySign = WxPayUtil.getWxPaySign(map, appSecret);
        Element sign = rootElement.element("sign");

        return wxPaySign.equalsIgnoreCase(sign.getText().trim());
    }


    /**
     * @description 验签
     * @author aj
     * @date 2022/1/29 11:06
     * @param object 需要校验的object数据 非null 否则抛出参数异常的错误
     * @param appSecret 用来验签的密钥 非空 否则抛出参数异常的错误
     * @param validateSign 需要被校验的签名
     * @return boolean
     */
    public static boolean validateSign(Object object,String appSecret,String validateSign){
        if(null == object || StringUtils.isBlank(appSecret) || StringUtils.isBlank(validateSign)){
            throw new IllegalArgumentException("object appSecret validateSign must not empty.");
        }

        String sign = getWxPaySign(object, appSecret);

        return validateSign.equalsIgnoreCase(sign);
    }
}
