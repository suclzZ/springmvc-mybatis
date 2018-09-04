package com.sucl.study.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodReflect {

    public void f1(String name){
        System.out.println("f1 :" + name);
    }

    public static void f2(){
        System.out.println("f2");
    }

    public int add(int i){
        return i + 5;
    }

    public static void main(String[] args) throws Throwable {

        //m1
        MethodReflect mr = new MethodReflect();
        Method method = MethodReflect.class.getDeclaredMethod("f1",String.class);
        Class<?> mc = method.getDeclaringClass();
        run(mr, method,new String[]{"method"});
        //static
        Method method2 = MethodReflect.class.getDeclaredMethod("f2");
        runObj(mr, method2,null);
        //m2
        Method method3 = MethodReflect.class.getDeclaredMethod("add",int.class);
        Object result = run2(mr, method3, new Integer[]{10});
        System.out.println("add :" + result);
    }

    public static void run(Object obj , Method method , Object[] args) throws Throwable {
        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        Class<?> methodClass = method.getDeclaringClass();
        MethodHandles.Lookup methodIns = constructor.newInstance(methodClass, MethodHandles.Lookup.PUBLIC|MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC);
        methodIns.unreflectSpecial(method,methodClass).bindTo(obj).invokeWithArguments(args);
    }

    public static void runObj(Object obj , Method method , Object[] args) throws Throwable {
        MethodHandles.Lookup methodIns = MethodHandles.lookup();
        MethodHandle methodHandle = methodIns.findStatic(obj.getClass(), method.getName(), MethodType.methodType(void.class));
        methodHandle.invoke();
    }

    public static Object run2(Object obj , Method method , Object[] args) throws Throwable {
        Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        Class<?> methodClass = method.getDeclaringClass();
        MethodHandles.Lookup methodIns = constructor.newInstance(methodClass, MethodHandles.Lookup.PUBLIC|MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC);
        MethodHandle mh = methodIns.findSpecial(obj.getClass(), method.getName(), MethodType.methodType(int.class, int.class),methodClass);//一般方法
//        MethodHandle mh = methodIns.findVirtual(obj.getClass(), method.getName(), MethodType.methodType(int.class, int.class)); //私有方法
        return mh.bindTo(obj).invokeWithArguments(args);
    }
}

