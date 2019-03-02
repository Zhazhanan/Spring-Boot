package com.example.websocketdistributed.redismq;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @author 七脉 描述：订阅监听类
 */
@AllArgsConstructor
public class SubscribeListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeListener.class);

    private StringRedisTemplate stringRedisTemplate;

    private Session session;

    /**
     * 订阅接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody());
        LOGGER.info("onMessage::message = {}, pattern = {}, 主题发布 = {}", message, pattern, msg);
        if (null != session) {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
