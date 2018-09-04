package com.sucl.mybatis.mapper;

import org.apache.ibatis.jdbc.SQL;

public class UserSelectProvider {

    public String getUser(String id ,String p2){
        System.out.println("getUser :" + id +","+p2);
        String select = new SQL().SELECT("*")
                .FROM("user")
                .WHERE("user_id = "+id).toString();
        String select2 =  "select * from user where user_id = "+id;
        return select;
    }
}
