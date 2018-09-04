package com.sucl.mybatis.xml.support;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import com.sucl.mybatis.model.User;

import java.util.List;
import java.util.Properties;

/**
 * 仅用于创建对象
 * SomeObjectFactory someObjectFactory = new SomeObjectFactory();
 * someObjectFactory.create(XX.class);
 */
public class SomeObjectFactory extends DefaultObjectFactory {

    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);
        if(User.class.isInstance(t)){
            User user = (User) t;
            user.setJob("auto");
        }
        return t;
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        System.err.println("someProperty :" + properties.getProperty("someProperty"));
        super.setProperties(properties);
    }

    @Override
    protected Class<?> resolveInterface(Class<?> type) {
        return super.resolveInterface(type);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
