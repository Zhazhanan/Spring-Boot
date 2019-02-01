package com.example.flowable.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author WangKun
 * @create 2019-02-01
 * @desc
 **/
@RestController
public class HellowFlowable {

    @GetMapping("/hello")
    public String hello(Principal principal) {
        return "Hello " + principal.getName() + "!";
    }

}
