package com.sucl.mybatis.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userId;
    private String username;
    private String password;
    private int age;
    private String job;
    private String phone;
    private String email;
    private String createDate;
    private boolean online;

}
