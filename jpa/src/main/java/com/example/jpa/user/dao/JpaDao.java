package com.example.jpa.user.dao;

import com.example.jpa.user.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-28 上午 9:12
 * @desc
 **/
@Repository
public interface JpaDao extends JpaRepository<User, Long> {

    @Query("select a.id,a.name,a.password from User a where name = :name")
    List<User> findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(Long id);
}
