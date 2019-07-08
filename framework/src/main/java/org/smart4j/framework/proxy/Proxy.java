package org.smart4j.framework.proxy;

/**
 * Created by HinTi on 2019/7/8.
 * Goal:
 */
public interface Proxy {
    /**
     * 传入一个proxyChain, 用于执行链式代理操作
     * 将多个代理通过一条链子串起来，一个个的去执行，执行顺序取决于添加到链上的先后顺序
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object   doProxy(ProxyChain proxyChain) throws Throwable;
}