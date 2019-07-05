package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: ClassHelper 类可以获取所加载的类，但是无法通过类来实例化对象。因为需要提供一个反射工具类，来封装java反射相关的API，对外提供更好用的工具方法。
 */
public class ReflectionUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建类的实例
     * @param cls 给定类名
     * @return 实例
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance error",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj 实例
     * @param method 方法
     * @param args 方法参数
     * @return
     */
    public static Object invokeMethod(Object obj, Method method,Object[] args){
        Object result;
        method.setAccessible(true);
        try {
            result = method.invoke(obj,args);
        } catch (Exception e) {
            LOGGER.error("method invoke error",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param obj 类示例
     * @param field 字段
     * @param value 值
     */
    public static void setFiled(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}