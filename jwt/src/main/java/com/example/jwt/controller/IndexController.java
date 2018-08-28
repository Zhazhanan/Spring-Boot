package com.example.jwt.controller;

import com.example.jwt.util.JwtUtil;
import com.example.jwt.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangKun
 * @create 2018-08-28
 * @desc
 **/
@RestController
public class IndexController {

    @GetMapping("/api/index")
    public String index() {
        return "hellow world";
    }

    @PostMapping("/login")
    public Map login(User user) {
        String jwt = JwtUtil.generateToken(user.getName());
        return new HashMap<String, String>() {{
            put("token", jwt);
        }};

    }
}
