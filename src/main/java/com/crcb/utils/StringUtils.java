package com.crcb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Classname StringUtils
 * @Description 字符串工具类
 * @Date 2020/3/18 14:33
 * @Created by gangye
 */
public class StringUtils {
    public final static String REG_DIGIT = "[0-9]*";
    public final static String REG_CHAR = "[a-zA-Z]*";
    public final static String EMPTY = "";

    /**
     * 判断是否为空
     */
    public static boolean isEmpty(Object... obj) {
        if(obj == null)
            return true;
        for(Object object : obj) {
            if (object == null)
                return true;
            if (object.toString().trim().length() == 0)
                return true;
        }

        return false;
    }

    public static boolean isBlankEmpty(Object obj) {
        if (obj == null || "".equals(obj) || "".equals(obj.toString().trim()) || "null".equalsIgnoreCase(obj.toString()))
            return true;

        return false;
    }

    /**
     * 是否空,或者为空串,或者为"null"
     * @author guoyu
     */
    public static boolean isBlankEmpty(Object... objs) {
        if (objs == null || objs.length == 0)
            return true;
        for (Object obj : objs) {
            if (isBlankEmpty(obj)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotBlank(String pattern) {
        return !isBlankEmpty(pattern);
    }

    public static boolean isBlank(String pattern) {
        return isBlankEmpty(pattern);
    }

    public static String formatCountNames(String nameList) {
        String[] names = nameList.split(",");

        Map<String, Integer> nameCount = new HashMap<String, Integer>();
        for(String name : names) {
            if(StringUtils.isEmpty(name)) continue;
            if(nameCount.containsKey(name)) {
                Integer count = nameCount.get(name) + 1;
                nameCount.put(name, count);
            } else {
                nameCount.put(name, 1);
            }
        }

        StringBuilder newNames = new StringBuilder();
        for(String key : nameCount.keySet()) {
            if(StringUtils.isEmpty(key)) continue;
            Integer count = nameCount.get(key);
            String splitChar = newNames.length() > 0 ? "," : "";
            newNames.append(splitChar).append(key).append("x").append(count);
        }

        return newNames.toString();
    }


    public static boolean isDigit(String str){
        return isNumeric(str);
    }

    public static boolean isChar(String str){
        return str.matches(REG_CHAR);
    }

    public static Boolean isNotEmpty(Object... obj) {
        Boolean r = StringUtils.isEmpty(obj);
        return !r;
    }

    public static boolean isNumeric(String str) {
        if(isBlankEmpty(str)) return false;
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * string 中的 str 在倒数 num 中的位置
     * @author guoyu
     */
    public static int stringLastlndex(String string, String str, int num) {
        int indexOf = string.lastIndexOf(str);
        if(num > 1){
            return stringLastlndex(string.substring(0, indexOf), str, num - 1);
        } else {
            return indexOf;
        }
    }


    public static String getValue(Object val) {
        return val == null ? "" : val.toString().replace("\n", "");
    }

    public static String getFileName(boolean type, Date startDate, String tableName){
        String dateString = dateFormat(startDate,"yyyyMMdd");
        StringBuffer stringBuffer = new StringBuffer(dateString);
        stringBuffer.append("_");
        stringBuffer.append(tableName);
        stringBuffer.append("_");
        if (type) {
            stringBuffer.append("insert&");
        } else {
            stringBuffer.append("update&");
        }
        return stringBuffer.toString();
    }

    public static String getRefundNumber(Integer payRefundNum) {
        if (payRefundNum == null) payRefundNum = 0;
        payRefundNum = payRefundNum + 1;
        String string = String.valueOf(payRefundNum);
        if (string.length() == 1) {
            return "0"+string;
        }
        return string;

    }

    private static String dateFormat(Date date, String datePattern) {
        if(date == null) return "";
        if(datePattern == null) datePattern = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(datePattern, Locale.UK);
        return df.format(date);
    }
}
