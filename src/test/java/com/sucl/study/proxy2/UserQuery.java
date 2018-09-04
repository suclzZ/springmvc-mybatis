package com.sucl.study.proxy2;

import com.sucl.mybatis.model.User;

public class UserQuery implements IQuery<User>{

    @Override
    public User query(String id) {
        System.out.println("query");
        return null;
    }

    @Override
    public void update(User user, String id) {
        System.out.println("update");
    }

    @Override
    public void save(User user) {
        System.out.println("save");
    }

    @Override
    public boolean delete(String id) {
        System.out.println("delete");
        return false;
    }
}
