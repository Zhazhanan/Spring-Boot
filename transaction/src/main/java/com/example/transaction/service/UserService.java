package com.example.transaction.service;

import com.example.transaction.dao.UserDao;
import com.example.transaction.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @classname: LaTAdvfinishedService
 * @description: 定义  LA_T_ADVFINISHED 实现类
 * @author: Administrator
 */
@Service()
public class UserService implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserDao dao;

    //    @Transactional(propagation = Propagation.REQUIRED)
    public int insert(User user) {
        int insert = dao.insert(user);
//        if (true) {
//            throw new RuntimeException("it is error");
//        }
        return insert;
    }

    public void save(User user) {
        dao.save(user);
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    //    @Transactional(propagation = Propagation.REQUIRED)
    public void update(User user) {
//        try {
        dao.update(user);
        if (true) {
            throw new RuntimeException("it is error");
        }
//        } catch (RuntimeException e) {
//            LOGGER.error("update::user = {}，e = {}", user, e);
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
    }

    @Transactional
    public void insertUpdate(User user) throws Exception {
        insert(user);
        user.setPassword("0000");
        update(user);
    }
}


