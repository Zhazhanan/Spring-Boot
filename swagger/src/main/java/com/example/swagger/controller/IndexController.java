package com.example.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangKun
 * @create 2018-08-10
 * @desc
 **/
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "it is ok";
    }
}
