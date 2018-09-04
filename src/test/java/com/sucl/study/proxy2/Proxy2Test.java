package com.sucl.study.proxy2;

import org.apache.ibatis.plugin.Interceptor;

/**
 *  定义拦截器，实现Interceptor
 *  对应两个方法 intercepte , getObject
 *  对应一个注解 @Interceptors,主要指明代理的接口，方法，参数，代理对象必须是该接口的实现类
 *  首先 由Proxy 包装 目标类target,
 *  在自定义的Interceptor.intercepte写相关的代理业务
 *   proxy.method -> Interceptor.intercepte -> target.method
 */
public class Proxy2Test {

    public static void main(String[] args) {
        Interceptor interceptor = new UserQueryInterceptor();
        IQuery query = new UserQuery();//必须实现IQuery,与interceptor中定义的一致
        IQuery proxy = (IQuery) interceptor.plugin(query);
        proxy.query(null);
    }
}
