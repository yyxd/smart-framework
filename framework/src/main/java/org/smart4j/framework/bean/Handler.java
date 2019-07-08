package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by HinTi on 2019/7/6.
 * Goal: 封装Action信息
 *
 */
public class Handler {
    // Controller 类
    private Class<?> controllerClass;
    // Action方法
    private Method actionMethod;
    public Handler(Class<?> controllerClass,Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }
    public Class<?> getControllerClass(){
        return controllerClass;
    }

    public Method getActionMethod(){
        return actionMethod;
    }



}