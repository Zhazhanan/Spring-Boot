package com.example.websoket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @DES: ${DESCRIPTION}
 * @Author: wangK
 * @Date: Created in 23:15 2018/11/1
 */
@Controller
public class PageController {

    //页面请求
    @GetMapping("/socket/{cid}")
    public String home(@PathVariable String cid, Model model) {
        model.addAttribute("cid", cid);
        return "/home";
    }
}
