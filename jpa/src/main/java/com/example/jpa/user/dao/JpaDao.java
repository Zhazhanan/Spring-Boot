package com.example.jpa.user.dao;

import com.example.jpa.user.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author WangKun
 * @create 2018-08-28 上午 9:12
 * @desc
 **/
public interface JpaDao extends JpaRepository<User, Long> {

}
