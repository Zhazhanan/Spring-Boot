package com.example.swaggerbootstrapuidemo.user.dao;

import com.example.swaggerbootstrapuidemo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-28 上午 9:12
 * @desc
 **/
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("delete from User s where s.id in (?1)")
    void deleteBatch(List<Long> ids);
}
