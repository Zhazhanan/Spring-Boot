package com.example.swagger2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author WangKun
 * @create 2018-09-10
 * @desc
 **/
@Controller
public class APIController {
    @GetMapping(value = "/api")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
