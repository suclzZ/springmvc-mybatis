package com.sucl.study.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        IProxy target = new ProxyTarget();
        ProxyFactory<IProxy> proxyFactory = new ProxyFactory<IProxy>(IProxy.class);
        AnyProxy<IProxy> anyProxy = new AnyProxy<>();
        anyProxy.setTarget(target);
        IProxy proxy = proxyFactory.newInstance(anyProxy);
        proxy.invoke(null);

        IProxy proxy1 = (IProxy) Proxy.newProxyInstance(IProxy.class.getClassLoader(),new Class[]{IProxy.class},anyProxy);
        proxy1.invoke(null);

    }
}
