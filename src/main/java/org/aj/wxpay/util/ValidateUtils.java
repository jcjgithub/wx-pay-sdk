package org.aj.wxpay.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>Company: www.iotimc.com</p>
 *
 * @description: 校验性工具目前用于检验实体类非空之类的检验
 * @author: jcj
 * @date: 2020/6/11 14:28
 **/
public class ValidateUtils {




    public static void validateString(String value,String paramName) {
        if(StringUtils.isBlank(value)){
            throw new IllegalArgumentException(paramName+" must not empty");
        }
    }

    public static void validateFee(Integer value,String paramName) {
        if(null == value){
            throw new IllegalArgumentException("paramName must not null");
        }
        if(value < 0){
            throw new IllegalArgumentException("paramName must not less then 0");
        }
    }

    public static void validateTimeStart(String timeStart) {
        if(StringUtils.isBlank(timeStart))
            throw new IllegalArgumentException("timeStart must not empty.");
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            df.parse(timeStart);
        } catch (ParseException e) {
            throw new IllegalArgumentException("sorry only allow yyyyMMddHHmmss");
        }
    }



}
