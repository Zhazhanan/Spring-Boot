package com.example.rocketmq.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class RocketMQClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQClient.class);
    private String producerGroup;
    private String namesrvAddr;

    public RocketMQClient(String producerGroup, String namesrvAddr) {
        this.producerGroup = producerGroup;
        this.namesrvAddr = namesrvAddr;
    }


    @PostConstruct
    public void defaultMQProducer() {
        LOGGER.info("-----------------------------defaultMQProducer:: init-----------------------------");
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);

        try {
            producer.start();
            StopWatch stop = new StopWatch();
            stop.start();
            Message message;
            for (int i = 0; i < 50000; i++) {
                message = new Message("TopicTest", "push", (i + "发送消息---------").getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult result = producer.send(message);
                LOGGER.info("ProducerMessage::MsgId = {},status = {}", result.getMsgId(), result.getSendStatus());
            }
            stop.stop();
            LOGGER.info("defaultMQProducer::发送消息耗时={}", stop.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
