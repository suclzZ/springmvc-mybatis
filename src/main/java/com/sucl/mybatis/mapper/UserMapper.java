package com.sucl.mybatis.mapper;

import com.sucl.mybatis.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

//@Mapper
public interface UserMapper {

    public User selectById(String userId);

    public List<User> selectAll();

    public void save(User user);

    public void update(User user);

    public boolean delete(String userId);

    @Select("select * from user where user_id = #{id}")
    public User get(String id);

    @SelectProvider(type = UserSelectProvider.class,method = "getUser")
    public User getUser(String id,String p2);
}
