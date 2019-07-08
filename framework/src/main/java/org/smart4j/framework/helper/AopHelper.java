package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotaion.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by HinTi on 2019/7/8.
 * Goal: 获取所有目标类，以及被拦截的切面类实例,并且通过ProxyManager#createProxy方法来创建代理对象
 * 代理类与目标类之间实际上是多对多的关系，一个代理类可以被用在多个目标类之上，一个目标类可以使用多个代理
 */
public final class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
    // 静态类初始化AOP框架
    static {
        try {
            // 获取代理类与目标类之间的映射关系
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            // 获取目标类与代理实例之间的关系
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);

            for(Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                // 创建代理
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure",e);
        }
    }
    /**
     * 获取Aspect注解中设置的注解类，如果该注解类不是Aspect类，则可调用ClassHelper.getClassSetByAnnotation 方法获取相关类，并把这些类放入目标集合中，最终返回这个集合
     *
     * @param aspect 注解为Aspect的类
     * @return 注解为aspect.value()的所有类，要对这些类的方法进行拦截，创建代理
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation !=null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 用户实现的代理类需要继承AspectProxy抽象类，并且带有Aspect注解，只有满足这两个条件，才能根据Aspect 注解中所定义的注解属性去获取该注解所对应的目标类集合，然后才能建立代理类与目标类集合之间的映射关系
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<>();
        // 获取所有代理类，用户实现的代理类
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> proxyClass:proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);;
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
        return proxyMap;
    }

    /**
     * 在获取了代理类与目标类集合之间的映射关系后，建立目标类与代理对象列表之间的映射关系
     * 一个目标类可以匹配多个代理类，对这些代理类创建唯一实例，目标类与代理实例之间的映射关系
     * @param proxyMap 代理类与目标类结合之间的映射关系，代理多个目标类
     * @return 目标类与代理实例之间的关系，
     * @throws Exception
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet()){
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for(Class<?> targetClass :targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();// 利用反射创建代理类实例
                if(targetMap.containsKey(targetClass)){// 如果目标类已经存在一个或多个代理对象了，则将当前代理对象加入进去
                    targetMap.get(targetClass).add(proxy);
                }else { // 目标类还没有代理对象，创建代理对象列表，将当前代理对象加进去
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }
            }

        }
        return targetMap;
    }
}