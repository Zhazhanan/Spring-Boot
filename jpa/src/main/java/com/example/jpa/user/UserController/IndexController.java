package com.example.jpa.user.UserController;

import com.example.jpa.user.POJO.User;
import com.example.jpa.user.dao.JpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/user/{name}")
    public List<User> index(@PathVariable String name) {
        List<User> u = jpaDao.findByName(name);
        return u;
    }

    @GetMapping("/users/{name}")
    public List<User> users(@PathVariable String name) {
        System.out.println(jpaDao.count());
        return jpaDao.findAll();
    }

    @DeleteMapping("/user/del")
    public void del(Long id) {
        jpaDao.deleteById(id);
    }

    @PostMapping("/user/add")
    public User add(User user) {
        User save = jpaDao.save(user);
        return save;
    }

}
