package org.smart4j.framework.util;


/**
 * Created by HinTi on 2019/6/18.
 * Goal:
 */
public final class CastUtil {
    public static String castString(Object obj){
        return castString(obj,"");
    }

    public static String castString(Object obj,String defaultValue){
        return obj!=null? String.valueOf(obj):defaultValue;
    }

    /**
     * 转为double 型
     * @param obj
     * @return
     */
    public static double castDouble(Object obj){
        return castDouble(obj,0.0);
    }

    /**
     * 转为double型，可以指定默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj,double defaultValue){
        double doubleValue = defaultValue;
        if(obj!=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    doubleValue = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为long 型
     * @param obj
     * @return
     */
    public static long castLong(Object obj){
        return castLong(obj,0);
    }

    /**
     * 转为double型，可以指定默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue){
        long longValue = defaultValue;
        if(obj!=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    longValue = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }
    /**
     * 转为double 型
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    /**
     * 转为double型，可以指定默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj,int defaultValue){
        int intValue = defaultValue;
        if(obj!=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }
    /**
     * 转为double 型
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    /**
     * 转为double型，可以指定默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj!=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    booleanValue = Boolean.parseBoolean(strValue);
                }catch (NumberFormatException e){
                    booleanValue = defaultValue;
                }
            }
        }
        return booleanValue;
    }
}