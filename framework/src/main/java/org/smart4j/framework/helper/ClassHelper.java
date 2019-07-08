package org.smart4j.framework.helper;

import org.smart4j.framework.annotaion.Controller;
import org.smart4j.framework.annotaion.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: 类操作助手类，获取应用包名下的所有类，所有service/controller类
 * 带有Controller 和 Service注解的类所产生的对象，是由smart 框架所管理的bean
 */
public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static {
        String base_package = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(base_package);// 获取应用包名下的所有类集合
    }

    /**
     * 获取应用包名下的所有类
     * @return 所有类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> serviceClassSet = new HashSet<>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                serviceClassSet.add(cls);
            }
        }
        return serviceClassSet;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（实现类）
     * @param superClass 父类（或接口）
     * @return 子类（实现类）集合
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> cls:CLASS_SET){
            // isAssignableFrom 是当前类或者子类
            if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取带有某种注解的所有类
     * @param annotationClass 注解
     * @return 带有该注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass))
                classSet.add(cls);
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> controllerClassSet = new HashSet<>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                controllerClassSet.add(cls);
            }
        }
        return controllerClassSet;
    }

    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
}