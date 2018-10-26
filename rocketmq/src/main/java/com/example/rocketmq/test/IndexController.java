package com.example.rocketmq.test;

import com.alibaba.fastjson.JSONObject;
import com.example.rocketmq.config.bean.MQConsumer;
import com.example.rocketmq.config.bean.MQProducer;
import com.example.rocketmq.config.bean.Subscription;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

/**
 * @author WangKun
 * @create 2018-09-04
 * @desc
 **/
@RestController
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    MQProducer mqProducer;
    @Autowired
    MQConsumer mqConsumer;

    @GetMapping("/send")
    public SendResult index(Subscription subscription, UserInfo userInfo) {
        String uuid = UUID.randomUUID().toString();
        Random ra = new Random();
        userInfo.setId(ra.nextLong());
        userInfo.setPassword(String.valueOf(ra.nextFloat()));
        userInfo.setName(String.valueOf(ra.nextFloat()));
        Message message = null;
        try {
            message = new Message(subscription.getTopic(), subscription.getTag(), uuid, JSONObject.toJSONString(userInfo).getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mqProducer.sendMessage(message);
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
