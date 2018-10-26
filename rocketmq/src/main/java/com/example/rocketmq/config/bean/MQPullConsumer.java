package com.example.rocketmq.config.bean;

import com.example.rocketmq.config.exception.MQException;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author WangKun
 * @create 2018-09-04
 * @desc
 **/
public class MQPullConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQPullConsumer.class);

    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();
    private DefaultMQPullConsumer consumer;
    private String consumerGroup;
    private String namesrvAddr;

    public MQPullConsumer(String consumerGroup, String namesrvAddr) {
        this.consumerGroup = consumerGroup;
        this.namesrvAddr = namesrvAddr;
    }

    public void start() {
        try {
            if (null == consumerGroup || null == namesrvAddr) {
                throw new MQException("consumerGroup or namesrvAddr not set");
            }
            consumer = new DefaultMQPullConsumer(consumerGroup);
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.setInstanceName("PullConsumer");
            consumer.start();


            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicPull");
            for (MessageQueue mq : mqs) {
                System.out.println("Consume from the queue: " + mq);
                SINGLE_MQ:
                while (true) {
                    try {
                        PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                        List<MessageExt> list = pullResult.getMsgFoundList();
                        if (list != null && list.size() < 100) {
                            for (MessageExt msg : list) {
                                System.out.println(new String(msg.getBody()));
                            }
                        }
                        System.out.println(pullResult.getNextBeginOffset());
                        putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                        switch (pullResult.getPullStatus()) {
                            case FOUND:
                                break;
                            case NO_MATCHED_MSG:
                                break;
                            case NO_NEW_MSG:
                                break SINGLE_MQ;
                            case OFFSET_ILLEGAL:
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        if (this.consumer != null) {
            LOGGER.info("shutdown::");
            this.consumer.shutdown();
        }
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null) {
            System.out.println(offset);
            return offset;
        }
        return 0;
    }

}
