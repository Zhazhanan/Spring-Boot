package com.example.transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author WangKun
 * @create 2018-08-16
 * @desc
 **/
@Controller("/index")
public class IndexController {
    @GetMapping("/go")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "this a msg from HelloWorldController");
        mv.setViewName("index");
        return mv;
    }
}
