package com.sucl.study.proxy2;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * mybatis Plugin 相似功能
 */
public class ProxyHandler implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;
    private Map<Class, Set<Method>> targetMethods;

    public ProxyHandler(Object target,Interceptor interceptor,Map<Class, Set<Method>> targetMethods){
        this.target = target;
        this.interceptor = interceptor;
        this.targetMethods = targetMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods = targetMethods.get(method.getDeclaringClass());
        if(methods!=null && methods.contains(method)){
            return interceptor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,args);
    }

    public static Object warp(Object target, Interceptor interceptor){

        Map<Class, Set<Method>> targetMethods = findMethods(interceptor);
        Class<?> tagClass = target.getClass();
        Class[] proxyInterfaces = findProxyInterfaces(tagClass,targetMethods);
        return Proxy.newProxyInstance(tagClass.getClassLoader(),proxyInterfaces,new ProxyHandler(target,interceptor,targetMethods));
    }

    private static Class[] findProxyInterfaces(Class<?> tagClass, Map<Class,Set<Method>> targetMethods) {
        List<Class> interfaces = new ArrayList<>();
        while (tagClass!=null){
            for(Class tagInf : tagClass.getInterfaces()){
                if(targetMethods.containsKey(tagInf)){
                    interfaces.add(tagInf);
                }
            }
            tagClass = tagClass.getSuperclass();
        }
        return interfaces.toArray(new Class[interfaces.size()]);
    }

    private static Map<Class,Set<Method>> findMethods(Interceptor interceptor) {
        Intercepts interceptors = interceptor.getClass().getAnnotation(Intercepts.class);
        Signature[] signatures = interceptors.value();
        if(signatures==null){
            throw new RuntimeException(interceptor.getClass().getCanonicalName() +"signatures is null");
        }
        Map<Class,Set<Method>> intefMethods = new HashMap<>();
        for(Signature sign :signatures){
            Class<?> _interface = sign.type();
            String methodName = sign.method();
            Class<?>[] args = sign.args();
            Set<Method> methods = intefMethods.get(_interface);
            if(methods == null){
                methods = new HashSet<>();
                intefMethods.put(_interface,methods);
            }
            try {
                Method method = _interface.getMethod(methodName, args);
                methods.add(method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return  intefMethods;
    }
}
