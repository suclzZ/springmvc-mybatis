package com.sucl.mybatis.test;

import com.sucl.mybatis.core.SqlSessionFactoryResource;
import com.sucl.mybatis.mapper.UserMapper;
import com.sucl.mybatis.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.UUID;

public class UserMapperTest {

    public static void main(String[] args) {
        SqlSessionFactoryResource sqlSessionFactoryResource = new SqlSessionFactoryResource();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryResource.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        save(userMapper);
        sqlSession.commit(true);
    }

    public static void save(UserMapper userMapper){
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("name"+System.currentTimeMillis());
        user.setPassword("123456");
        user.setOnline(true);
        userMapper.save(user);
    }
}
