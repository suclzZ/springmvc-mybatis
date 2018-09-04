package com.sucl.study.proxy2;

import com.sucl.mybatis.model.User;

/**
 *  查询接口
 */
public interface IQuery<T> {

    T query(String id);

    void update(T t,String id);

    void save(T t);

    boolean delete(String id);
}
