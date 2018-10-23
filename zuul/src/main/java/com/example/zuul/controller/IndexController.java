package com.example.zuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangKun
 * @create 2018-08-30
 * @desc
 **/
@RestController
public class IndexController {
    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/")
    public String home() {
        return "Hello world ,port:" + port;
    }

    @GetMapping("/index")
    public String index(String name) {
        return name + " hello,this server and port is " + appName + ":" + port;
    }
}
