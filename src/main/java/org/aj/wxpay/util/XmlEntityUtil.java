package org.aj.wxpay.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import org.aj.wxpay.bean.request.CreateWxPayLinkRequest;
import org.aj.wxpay.exception.XmlEntityConvertException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @autor aj
 * @description xml跟实体类相互转换的工具类
 * @date 2022/1/2911:09
 */
public class XmlEntityUtil {



    /**
     * 得到指定xml标签里面指定的标签内容
     *
     * @param xmlStr  如果为空或者null，则返回空
     * @param tagName 如果为空或者为null,则返回空
     * @return 如果此标签不存在则返回null
     */
    public static String getXmlTagValue(String xmlStr, String tagName) {
        if (StringUtils.isBlank(xmlStr) || StringUtils.isBlank(tagName)) {
            return "";
        }
        xmlStr = xmlStr.trim();
        String tagValue = null;
        tagName = tagName.trim();
        if (xmlStr.contains("<" + tagName + ">")) {
            int i = xmlStr.indexOf("<" + tagName + ">");
            int length = ("<" + tagName + ">").length();
            tagValue = StringUtils.substring(xmlStr, i + length, xmlStr.indexOf("</" + tagName + ">"));
            if (tagValue.contains("CDATA")) {
                int start = tagValue.indexOf("CDATA[") + "CDATA[".length();
                System.out.println(start);
                tagValue = StringUtils.substring(tagValue, start, tagValue.indexOf("]]>"));
            }
        }
        return tagValue;
    }

    /**
     * 把xml数据封装到对应的实体类型，目前会把数据封装到 Integer,Double,Long,String ,Boolean里面去
     * 其他的没有做封装 ,注意此实体类的属性是托驼峰命名法
     *
     * @param xmlStr 需要解析的xml数据
     * @param clazz  对应的实体类的字节码
     * @param <T>
     * @return 参数错误或者解析失败返回null
     * @author aj
     */
    public static <T> T packageXmlDataToEntity(String xmlStr, Class<T> clazz)  {
        if (StringUtils.isBlank(xmlStr) || null == clazz) {
            throw new RuntimeException("param is null");
        }

        Document document;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
          throw new XmlEntityConvertException("parse xml error",e);
        }
        return xmlDataToEntity(document,clazz,true);
    }

    /**
     * 把xml数据封装到对应的实体类型，目前会把数据封装到 Integer,Double,Long,String ,Boolean里面去
     * 其他的没有做封装,属性名称的命名规则是驼峰法
     *
     * @param document  利用dom4j解析出来的document
     * @param clazz       对应的实体类的字节码
     * @param <T>
     * @return 参数错误或者解析失败返回null
     * @author aj
     */
    public static <T> T packageXmlDataToEntity(Document document, Class<T> clazz) {
        return xmlDataToEntity(document, clazz,true);

    }


    /**
     * 把xml数据封装到实体类里面去
     * @param document  利用dom4j 解析得到的文档对象
     * @param clazz  等待封装的实体类对于的字节码对象
     * @param isHump  实体的命名规则是不是驼峰命名
     * @param
     * @return <T>  若没有封装进去则返回一个空的实体类
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static <T> T xmlDataToEntity(Document document, Class<T> clazz,Boolean isHump) {
        if (null == document || null == clazz) {
            throw new RuntimeException("param is null");
        }
        T instance = null;
        try {
            instance = clazz.newInstance();
            for (Iterator it = document.getRootElement().elementIterator(); it.hasNext(); ) {
                Element element = (Element) it.next();
                String name = element.getName();
                if(isHump){
                    //把下划线按照驼峰进行转换
                    name = StrChangeUtil.lineToHump(name);

                }

                String text = element.getTextTrim();
                if (StringUtils.isNotBlank(text)) {


                    setValue(instance,text,name);
                    //BeanUtil.setFieldValue(instance,name,text);
                }
            }
        } catch (Exception e) {
            throw  new XmlEntityConvertException("xmlToEntity error",e);
        }
        return instance;
    }


    /**
     * 把xml数据封装到对应的实体类型，目前会把数据封装到 Integer,Double,Long,String ,Boolean里面去
     * 其他的没有做封装 ,注意此实体类的属性名称跟xml的标签名 一一对应否则封装封装不进去
     *
     * @param xmlStr 需要解析的xml数据
     * @param clazz  对应的实体类的字节码
     * @param <T>
     * @return 参数错误或者解析失败返回null
     * @author aj
     */
    public static <T> T packageXmlAbsDataToEntity(String xmlStr, Class<T> clazz) {
        if (StringUtils.isBlank(xmlStr) || null == clazz) {
            throw new IllegalArgumentException("xmlStr is empty");
        }

        Document document;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            throw new XmlEntityConvertException("parse xml error",e);
        }
        return xmlDataToEntity(document,clazz,false);
    }



    /**
     * 给实体类的属性赋值
     * @param instance
     * @param text
     * @param fieldName 字段名称
     * @param <T>
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static <T> void setValue(T instance, String text ,String fieldName) {
        //Field declaredField = instance.getClass().getDeclaredField(fieldName);
        Field declaredField = ReflectUtil.getField(instance.getClass(),fieldName);
        if(null != declaredField){
            //设置访问属性
            declaredField.setAccessible(true);
            String simpleName = declaredField.getType().getSimpleName();
            Object value = handlerValue(text, simpleName);
            if (null != value) {
                try {
                    declaredField.set(instance,value);
                } catch (IllegalAccessException e) {
                   throw new XmlEntityConvertException("setValue error",e);
                }
            }
        }

    }

    private static Object handlerValue(String text, String simpleName) {
        Object value = null;
        switch (simpleName.toUpperCase()) {
            case "INTEGER":
                //调用set方法赋值
                value = Integer.parseInt(text);
                break;
            case "DOUBLE":
                value = Double.parseDouble(text);
                break;
            case "LONG":
                value = Long.parseLong(text);
                break;
            case "STRING":
                value = text;
                break;
            case "BOOLEAN":
                value = Boolean.parseBoolean(text);
                break;
        }
        return value;
    }


    /**
     * 把实体类型拼接成xml形式的字符串，忽略掉为空和为null的字段 <xml><属性名称>属性值<属性名称/></xml>
     * 通过反射拿到属性描述器
     * @param bean 一定需要符合javabean 规范
     * @param humpToLine 是否把属性名称 驼峰转成下划线作为xml标签
     * @return
     */
    public static String getPropertyNameAndValueToXml(Object bean,boolean humpToLine)  {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        Class voClass = bean.getClass();
        PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(voClass);

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            String displayName = propertyDescriptor.getDisplayName();
            if(humpToLine){
                displayName = StrChangeUtil.humpToLine(displayName);
            }
            Object res ;

            try {
                res = propertyDescriptor.getReadMethod().invoke(bean);
            } catch (Exception e) {
               throw new XmlEntityConvertException("entity to xml when get ReadMethod error",e);
            }

            if (!displayName.equals("class")) {
                if (null != res) {
                    if (res instanceof String) {

                        if (StringUtils.isBlank((String) res) || "null".equalsIgnoreCase((String) res)) {
                            continue;
                        }
                    }
                    sb.append("<" + displayName + ">" + res + "</" + displayName + ">");

                }
            }

        }
        sb.append("</xml>");
        return sb.toString();
    }


    /**
     * 把实体类型拼接成xml形式的字符串，忽略掉为空和为null的字段 <xml><属性名称>属性值<属性名称/></xml>
     * 通过反射拿到 字段 不包含 静态 跟final
     * @param object
     * @param humpToLine 是否把属性名称 驼峰转成下划线作为xml标签
     * @return
     */
    public static String getPropertyNameAndValueToXmlByFiled(Object object,boolean humpToLine){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        Field[] fields = ReflectUtil.getFields(object.getClass());
        for (Field field : fields) {
            //设置可见性
            field.setAccessible(true);
            //拿到字段名称
            String name = field.getName();
            if(humpToLine){
                name = StrChangeUtil.humpToLine(name);
            }
            if(!Modifier.isStatic(field.getModifiers()) && ! Modifier.isFinal(field.getModifiers())){ //静态 final 字段不参与
                //拿到字段值
                Object o ;
                try {
                    o = field.get(object);
                } catch (IllegalAccessException e) {
                    throw new XmlEntityConvertException("entity to xml when get field value error",e);
                }
                if( null != o ){
                    if (o instanceof  String && StringUtils.isBlank((String) o)) {
                        continue;
                    }
                    sb.append("<" + name + ">" + o + "</" + name + ">");
                }
            }

        }

        sb.append("</xml>");
        return sb.toString();
    }


    public static void main(String[] args) {

        String outTradeNo = "002";
        String notifyUrl = "https://yd.ymyimi.cn:9053/fs-call-service//wx/pay/test/notify";

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        String timeStart = df.format(Calendar.getInstance().getTime());
        CreateWxPayLinkRequest createWxPayLinkRequest = new CreateWxPayLinkRequest("驿米爱心套餐",""
                ,10,"61.140.179.201",timeStart,notifyUrl);

        //String s = ValidateUtils.validateDefault(createWxPayLinkRequest);
        TreeMap<String, Object> propertyNameAndValue = WxPayUtil.getPropertyNameAndValue(createWxPayLinkRequest);

        System.out.println(propertyNameAndValue);
    }
}
