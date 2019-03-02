package com.example.wkredis.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author WangKun
 * @create 2019-03-01
 * @desc 发布
 **/
@Component
public class PublishService {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * @param channel 消息发布订阅 主题
     * @param message 消息信息
     * @author 发布方法
     */
    public void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
