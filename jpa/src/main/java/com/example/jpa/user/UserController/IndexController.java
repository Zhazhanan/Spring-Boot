package com.example.jpa.user.UserController;

import com.example.jpa.user.POJO.User;
import com.example.jpa.user.dao.JpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author WangKun
 * @create 2018-08-28
 * @desc
 **/
@RestController
public class IndexController {

    @Autowired
    private JpaDao jpaDao;


    @GetMapping("/index")
    public User index(Long id) {
        Optional<User> opt = jpaDao.findById(id);
        return opt.get();
    }
}
