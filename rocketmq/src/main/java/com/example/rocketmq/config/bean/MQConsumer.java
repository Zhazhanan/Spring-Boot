package com.example.rocketmq.config.bean;

import com.example.rocketmq.config.exception.MQException;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class MQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQConsumer.class);

    private DefaultMQPushConsumer consumer;
    private String consumerGroup;
    private String namesrvAddr;
    private Map<Subscription, MessageListener> subscriptionTable;

    public MQConsumer(String consumerGroup, String namesrvAddr, Map<Subscription, MessageListener> subscriptionTable) {
        this.consumerGroup = consumerGroup;
        this.namesrvAddr = namesrvAddr;
        this.subscriptionTable = subscriptionTable;
    }

    public void start() {
        LOGGER.info("start::");
        try {
            if (subscriptionTable == null) {
                throw new MQException("subscriptionTable not set");
            } else if (null == consumerGroup || null == namesrvAddr) {
                throw new MQException("consumerGroup or namesrvAddr not set");
            }
            this.consumer = new DefaultMQPushConsumer(consumerGroup);
            this.consumer.setConsumeMessageBatchMaxSize(10);
            this.consumer.setNamesrvAddr(namesrvAddr);
            this.consumer.setInstanceName("Consumer");
            this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            Iterator it = this.subscriptionTable.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Subscription, MessageListener> next = (Map.Entry) it.next();
                Subscription subscription = next.getKey();
                consumer.subscribe(subscription.getTopic(), subscription.getTag());
                consumer.setMessageListener(next.getValue());
            }
            this.consumer.start();
        } catch (MQClientException e) {
            throw new MQException(String.format("RocketMQConsumer start Exception, %s", e.getMessage()));
        }
    }

    public void shutdown() {
        if (this.consumer != null) {
            LOGGER.info("shutdown::");
            this.consumer.shutdown();
        }
    }

}
