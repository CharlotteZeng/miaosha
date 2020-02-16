package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from USER where id = #{id}")
    public User getUserById(@Param("id")int id);
    @Insert("insert into USER (id,name) Value(#{id},#{name})")
    public void insert(User u1);
}
