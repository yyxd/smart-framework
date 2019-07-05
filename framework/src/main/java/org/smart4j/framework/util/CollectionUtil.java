package org.smart4j.framework.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by HinTi on 2019/6/18.
 * Goal:
 */
public class CollectionUtil {
    public static boolean isEmpty(Collection<?> collections){
        return CollectionUtils.isEmpty(collections);
    }
    public static boolean isNotEmpty(Collection<?> collections){
        return !isEmpty(collections);
    }
    public static boolean isEmpty(Map<?,?>map){
        return MapUtils.isEmpty(map);
    }
    public static boolean isNotEmpty(Map<?,?>map){
        return !isEmpty(map);
    }
}