package com.example.websocketdistributed.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("websocket")
public class WebsocketController {

    @Value("${server.port}")
    private String port;

    /**
     * @param topic  发布订阅的频道主题
     * @param openid 发布者的显示名称
     *               描述：聊天页
     */
    @RequestMapping("index/{topic}/{openid}")
    public String index(@PathVariable("topic") String topic, @PathVariable("openid") String openid, Model model) {
        model.addAttribute("port", port);
        model.addAttribute("topic", topic);
        model.addAttribute("openid", openid);
        return "/websocket/index";
    }
}
