package com.example.rocketmq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class RocketMQServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQServer.class);
    private String namesrvAddr;
    private String consumerGroup;

    @Autowired
    private RocketMQListener rocketMQListener;

    public RocketMQServer(String consumerGroup, String namesrvAddr) {
        this.consumerGroup = consumerGroup;
        this.namesrvAddr = namesrvAddr;
    }

    @PostConstruct
    public void defaultMQPushConsumer() {
        LOGGER.info("-----------------------------defaultMQPushConsumer:: init-----------------------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            consumer.subscribe("TopicTest", "push");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setMessageListener(rocketMQListener);
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
