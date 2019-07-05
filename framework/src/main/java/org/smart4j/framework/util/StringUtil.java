package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by HinTi on 2019/6/18.
 * Goal: 对Apache Commons 类库进行包装字符串工具类
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        if(str!=null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}