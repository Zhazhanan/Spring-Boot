package com.example.rocketmq.config;

import com.example.rocketmq.config.bean.MQConsumer;
import com.example.rocketmq.config.bean.MQProducer;
import com.example.rocketmq.config.bean.Subscription;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class MQConfig {
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;
    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private String consumerGroup;

    public static final String TOPIC = "TOPIC_JHXT_CREDIT_WWW";
    public static final String TAG = "TAG_RECONSIDER_INFO";


    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public MQProducer rocketMQProducer() {
        return new MQProducer(producerGroup, namesrvAddr);
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public MQConsumer rocketMQConsumer() {
        Map<Subscription, MessageListener> map = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTag(TAG);
        subscription.setTopic(TOPIC);
//        Subscription subscription2 = new Subscription();
//        subscription2.setTopic("TOPIC_KK");
//        subscription2.setTag("TAG_KK");
        map.put(subscription, new MQPushListener());
//        map.put(subscription2, new MQPushListener());
        return new MQConsumer(consumerGroup, namesrvAddr, map);
//        return new MQPullConsumer(consumerGroup, namesrvAddr);
    }
}
