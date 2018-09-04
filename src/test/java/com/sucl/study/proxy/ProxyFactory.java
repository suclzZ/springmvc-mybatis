package com.sucl.study.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    private Class<T> iproxy;

    public ProxyFactory(Class<T> iproxy){
        this.iproxy = iproxy;
    }

    public T newInstance(AnyProxy<T> anyProxy){
        Object proxy = Proxy.newProxyInstance(iproxy.getClassLoader(), new Class[]{iproxy}, anyProxy);
        return (T)proxy;
    }
}
