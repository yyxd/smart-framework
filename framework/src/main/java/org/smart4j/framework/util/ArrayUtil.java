package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by HinTi on 2019/7/6.
 * Goal:
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
}