package com.example.wkredis.wk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * <br> Created by WangKun on 2019/05/09
 * <br>
 **/
@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/get")
    public Student get() {
        return demoService.get();
    }

    @GetMapping("/set")
    public Student set(int age) {
        return demoService.set(age);
    }
}
