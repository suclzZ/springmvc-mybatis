package com.sucl.study.proxy;

public class ProxyTarget implements IProxy{
    @Override
    public Object invoke(Object param) {
        System.out.println(this.getClass().getName());
        return null;
    }
}
