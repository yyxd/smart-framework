package org.smart4j.framework.helper;

import org.smart4j.framework.annotaion.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: 实现依赖注入功能
 * 实例化一个类别，不是开发者通过new的方式来实例化，而是通过框架自身来实例化，像这类实例化的过程，称为IOC (控制反转）
 * DI（依赖注入）将某个类需要依赖的成员注入类中
 * 这里使用的方法是先创建出所有的bean，再对bean中的filed进行注入
 */
public class IocHelper {
    static {
        // 获取bean 类与bean 实例之间的映射关系
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历bean map
            for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取类的字段并遍历
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    for(Field beanField: beanFields){
                        // 判断字段是否有Inject 注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            // 在beanMap 中获取类的实例
                            Object beanFiledInstance = beanMap.get(beanFieldClass);
                            if(beanFiledInstance!=null){
                                // 通过反射初始化BeanField 的值
                                ReflectionUtil.setFiled(beanInstance,beanField,beanFiledInstance);
                            }
                        }
                    }
                }
            }

        }
    }
}