package com.sucl.study.proxy2;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 *  该拦截器拦截的是target对象，具体的拦截业务在intercepter中实现
 *  其中target必须实现Intercepts注解中对应的Signature->type接口，方法与参数
 *  我们可以写多个Signature从而实现对多个接口的实现类有相同的业务逻辑
 */
@Intercepts({@Signature(type=IQuery.class,method = "query",args = {String.class})})
public class UserQueryInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("UserQueryInterceptor before");
        Object result = invocation.proceed();
        System.out.println("UserQueryInterceptor after");
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return ProxyHandler.warp(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
