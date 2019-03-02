package com.example.websocketdistributed.websocket;

import com.example.websocketdistributed.redismq.PublishService;
import com.example.websocketdistributed.redismq.SubscribeListener;
import com.example.websocketdistributed.util.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ServerEndpoint(value="/websocket")value值必须以/开路 备注:@ServerEndpoint注解类不支持使用@Autowire
 * {topic}指：向哪个频道主题里发消息
 * {openid}指：这个消息是谁的。真实环境里可以使用当前登录用户信息
 */
@Component
@ServerEndpoint(value = "/websocket/{topic}/{openid}")
public class WebsocketEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketEndpoint.class);

    /**
     * 因为@ServerEndpoint不支持注入，所以使用SpringUtils获取IOC实例
     */
    private StringRedisTemplate redisTampate = ApplicationContextProvider.getBean(StringRedisTemplate.class);

    private RedisMessageListenerContainer redisMessageListenerContainer = ApplicationContextProvider.getBean(RedisMessageListenerContainer.class);

    // 存放该服务器该ws的所有连接。用处：比如向所有连接该ws的用户发送通知消息。
    private static CopyOnWriteArraySet<WebsocketEndpoint> sessions = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("topic") String topic) {
        LOGGER.info("Websocket is Open:: topic = {}", topic);
        this.session = session;
        sessions.add(this);
        //设置订阅topic
        redisMessageListenerContainer.addMessageListener(
                new SubscribeListener(redisTampate, session),
                new ChannelTopic(topic));
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("Websocket is Close");
        sessions.remove(this);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("topic") String topic, @PathParam("openid") String openid) throws IOException {
        message = openid + "：" + message;
        System.out.println("java websocket 收到消息==" + message);
        LOGGER.info("Websocket receive message::  message = {}, topic = {}, openid = {}", message, topic, openid);
        PublishService publishService = ApplicationContextProvider.getBean(PublishService.class);
        publishService.publish(topic, message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.warn("Websocket Error:: error = {}", error);
    }

}
