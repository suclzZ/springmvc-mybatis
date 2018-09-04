package com.sucl.mybatis.xml.support;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 *  {@link OfIntercepter}
 * @see OfIntercepter
 */
@Intercepts(@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class}))
public class UpdateInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
