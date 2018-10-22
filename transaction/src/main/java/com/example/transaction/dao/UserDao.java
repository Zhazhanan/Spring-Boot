package com.example.transaction.dao;

import com.example.transaction.dto.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {
    @Insert("INSERT INTO user(ID,NAME,PASSWORD,CREATETIME) values(#{id},#{name},#{password},#{createTime})")
    int insert(User user);

    @Update("UPDATE user SET NAME=#{name},PASSWORD=#{password} WHERE ID =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE ID =#{id}")
    void delect(String id);

    void save(User user);
    void updateUser(User user);
}
