package org.smart4j.framework.proxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


/**
 * Created by HinTi on 2019/7/8.
 * Goal: 切面代理，调用ProxyManager
 * AspectProxy 类中的doProxy方法，我们从proxyChain 参数中获取了目标类、目标方法和方法参数，
 * 然后通过一个try...catch...finally 代码块来实现调用框架，从框架中抽象出一系列“钩子方法”
 * 这些抽象方法可在AspectProxy的子类中有选择性的进行实现
 * AspectProxy 的子类实现代理的内容
 */
public abstract class AspectProxy implements Proxy {

    public static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        begin();
        try {
            if(intercept(cls,method,params)){
                before(cls,method,params);
                result = proxyChain.doProxyChain();
                after(cls,method,params,result);
            }else {
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            LOGGER.error("proxy failure",e);
            error(cls,method,params,e);
            throw e;
        }finally {
            end();
        }
        return result;
    }

    public void begin(){

    }

    public boolean intercept(Class<?> cls,Method method,Object[] params) throws Throwable{
        return true;
    }

    public void before(Class<?> cls,Method method,Object[] params) throws Throwable{

    }

    public void after(Class<?> cls,Method method,Object[] params,Object result) throws Throwable{

    }

    public void error(Class<?> cls,Method method,Object[] params,Throwable e) throws Throwable{

    }

    public void end(){

    }
}