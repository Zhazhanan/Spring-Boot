package com.example.rocketmq.config.bean;

import com.example.rocketmq.config.exception.MQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class MQProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQProducer.class);

    private String producerGroup;
    private String namesrvAddr;
    private DefaultMQProducer producer;

    public MQProducer(String producerGroup, String namesrvAddr) {
        this.producerGroup = producerGroup;
        this.namesrvAddr = namesrvAddr;
    }

    public void start() {
        LOGGER.info("start::");
        if (StringUtils.isBlank(producerGroup) || StringUtils.isBlank(namesrvAddr)) {
            throw new MQException("producerGroup or namesrvAddr not set");
        } else {
            this.producer = new DefaultMQProducer(producerGroup);
            this.producer.setInstanceName("Producer");
            this.producer.setNamesrvAddr(namesrvAddr);
            try {
                this.producer.start();
            } catch (MQClientException e) {
                throw new MQException(String.format("RocketMQProducer start Exception, %s", e.getMessage()));
            }
        }
    }

    public void shutdown() {
        if (this.producer != null) {
            LOGGER.info("shutdown::");
            this.producer.shutdown();
        }
    }

    public SendResult sendMessage(Message message) {
        try {
            return this.producer.send(message);
        } catch (Exception e) {
            LOGGER.error("sendMessage::message = {},e ={}", message, e);
        }
        return null;
    }

}
