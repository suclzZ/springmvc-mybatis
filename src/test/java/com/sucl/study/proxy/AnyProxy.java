package com.sucl.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnyProxy<T> implements InvocationHandler {

    private T target;

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    /**
     *  proxy toString ->   super.toString();
     *  target toString ->  proxy.toString();
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy : "+proxy.getClass());
     //   method.invoke(this,args);
        method.invoke(target,args);

        return null;
    }

}
