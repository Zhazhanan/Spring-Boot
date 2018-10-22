package com.example.transaction.controller;

import com.example.transaction.dto.User;
import com.example.transaction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @classname: LaTAdvfinishedController
 * @description: 定义  LA_T_ADVFINISHED 控制层
 * @author: Administrator
 */
@RestController("/user")
@Scope("prototype")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @PostMapping(value = "/insert")
    public void insert(HttpServletRequest request, User user) throws Exception {
        try {
            service.insert(user);
        } catch (Exception e) {
            logger.error("insert：", e);
        }
        return;
    }

    @PostMapping(value = "/save")
    public void save(User user) throws Exception {
        service.save(user);
    }

    @PostMapping(value = "/updateUser")
    public void updateUser(User user) throws Exception {
        service.updateUser(user);
    }


    @PostMapping(value = "/update")
    public void update(HttpServletRequest request, User user) {
        try {
            service.update(user);
        } catch (Exception e) {
            logger.error("update:: e = {}", e);
        }
        return;
    }


    @PostMapping(value = "/insertUpdate")
    public void insertUpdate(HttpServletRequest request, User user) {
        try {
            service.insertUpdate(user);
        } catch (Exception e) {
            logger.error("insertUpdate:: e = {}", e);
        }
        return;
    }


}
