package com.example.swaggerbootstrapuidemo.user.service;

import com.example.swaggerbootstrapuidemo.user.dao.UserDao;
import com.example.swaggerbootstrapuidemo.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author WangKun
 * @create 2018-09-12
 * @desc
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public void saveBatch(List<User> user) {
        userDao.saveAll(user);
    }

    public void delete(Long id) {
        userDao.deleteById(id);
    }

    public void deleteBatch(List<Long> userIdList) {
        userDao.deleteBatch(userIdList);
    }

    public User update(User user) {
        Optional<User> optional = userDao.findById(user.getId());
        if (optional.isPresent()){
            User dbuser = optional.get();

        }
        return userDao.saveAndFlush(user);
    }

    public User get(Long id) {
        return userDao.findById(id).orElse(new User());
    }

    public Page<User> getList(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    public List<User> getList() {
        return userDao.findAll();
    }

}
