package org.aj.wxpay.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jcj
 * @description 字符串变形工具
 * @date 2021/11/2 18:01
 */
public class StrChangeUtil {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    private static Pattern linePattern = Pattern.compile("_(\\w)");


    /**
     * @description 驼峰转下划线
     * @author aj
     * @date 2021/12/7 12:30
     * @param str 若为空、已包含下划线、部包含下划线但是全部为大写 这几种情况则不会进行转换
     * @return java.lang.String 小写下划线分隔
     */
    public static String humpToLine(String str) {
        //若传入的字符串是已经包含了下划线 则不进行 按照驼峰转下划线
        if(StringUtils.isBlank(str) || str.contains("_") || StringUtils.isAllUpperCase(str))
            return str;


        Matcher matcher = humpPattern.matcher(str);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {

            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());

        }

        matcher.appendTail(sb);

        String string = sb.toString();

        //若首字母是下划线则去掉
        if(string.startsWith("_")){
            string = string.substring(string.indexOf("_")+1);
        }
        return string;

    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if(StringUtils.isBlank(str) || !str.contains("_"))
            return str;
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
