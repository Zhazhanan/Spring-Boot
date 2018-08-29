package com.example.shiro.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-27 下午 6:08
 * @desc
 **/
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    List<UserInfo> findByName(String name);

    List<UserInfo> findByNameAndPassword(String name, String password);
}
