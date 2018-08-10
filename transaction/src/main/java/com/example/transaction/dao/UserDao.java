package com.example.transaction.dao;

import com.example.transaction.dto.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface UserDao {
    @Insert("INSERT INTO user(ID,NAME,PASSWORD) values(#{id},#{name},#{password})")
    int insert(User user);

    @Update("UPDATE user SET NAME=#{name},PASSWORD=#{password} WHERE ID =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE ID =#{id}")
    void delect(String id);
}
