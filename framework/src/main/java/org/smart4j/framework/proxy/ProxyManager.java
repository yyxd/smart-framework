package org.smart4j.framework.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by HinTi on 2019/7/8.
 * Goal:
 */
public class ProxyManager {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        //CGLib 创建代理 ，传入的是代理的类和代理内容
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            //cglib 拦截器，包括代理的内容,返回的是方法值
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] params, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass,targetObject,targetMethod,methodProxy,params,proxyList).doProxyChain();
            }
        });
    }
}