package com.example.rocketmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class RocketMQConfig {
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;
    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private String consumerGroup;

    @Bean
    public RocketMQClient rocketMQClient() {
        return new RocketMQClient(producerGroup, namesrvAddr);
    }

    @Bean
    public RocketMQServer rocketMQServer() {
        return new RocketMQServer(consumerGroup, namesrvAddr);
    }

    @Bean
    public RocketMQListener rocketMQListener() {
        return new RocketMQListener();
    }
}
