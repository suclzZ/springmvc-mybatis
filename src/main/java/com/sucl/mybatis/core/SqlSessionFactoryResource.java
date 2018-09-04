package com.sucl.mybatis.core;

import com.sucl.mybatis.mapper.UserMapper;
import com.sucl.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class SqlSessionFactoryResource {
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public SqlSessionFactoryResource(){
        try {
            this.source();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void source() throws IOException {
        String evn = "development";
        Reader reader =  null;
        InputStream in = this.getClass().getResourceAsStream("config/mybatis-config.xml");
        in = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in, evn);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void crud(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User u1 = userMapper.get("1");
        System.out.println("u1 :" +u1);

        User u2 = userMapper.getUser("2","p2");
        System.out.println("u2 :" + u2);

        User user = userMapper.selectById("2");
        System.out.println( user);

        user.setPhone("13500000002");
        userMapper.update(user);
        System.out.println(user);

        sqlSession.close();
    }

    public static void main(String[] args) throws IOException {
        SqlSessionFactoryResource ssfr = new SqlSessionFactoryResource();
        ssfr.crud();
    }
}
