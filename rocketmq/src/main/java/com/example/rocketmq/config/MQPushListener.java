package com.example.rocketmq.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class MQPushListener implements MessageListenerConcurrently {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQPushListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<org.apache.rocketmq.common.message.MessageExt> msgs, ConsumeConcurrentlyContext context) {
        try {
            for (MessageExt messageExt : msgs) {
                System.out.println(new Date() + " Receive message, Topic is:" +
                        messageExt.getTopic() + ", keys is:" + messageExt.getKeys() + ", MsgId is:" + messageExt.getMsgId());
            }
        } catch (Exception e) {
            LOGGER.error("consumeMessage::e = {} ", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
