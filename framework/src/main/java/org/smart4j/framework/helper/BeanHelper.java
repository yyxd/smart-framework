package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: 获取smart框架管理的bean类，1.获取Bean类2.对于每个bean需要创建实例对象3. 将对象放在一个静态的Map<Class<?>,Object>中
 * Factory bean 创建的工厂
 * 相当于一个bean 容器
 */
public class BeanHelper {
    public static final Map<Class<?>,Object> BEAN_MAP = new HashMap<>();

    /**
     * 定义bean映射（用于存放bean 类与bean 实例的映射关系）
     */
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> beanClass:beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     * 获取bean 映射
     * @return bean_map
     */
    public static Map<Class<?>,Object> getBeanMap() {
        return BEAN_MAP;
    }
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean class: "+cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }
}