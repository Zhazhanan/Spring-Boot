package com.example.rocketmq.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-29
 * @desc
 **/
public class RocketMQListener implements MessageListenerConcurrently {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        try {
            for (MessageExt messageExt : msgs) {
                LOGGER.info("ConsumerMessage::msg = {}", messageExt);
                String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                LOGGER.info("ConsumerMessage::msgId = {},msgBody = {}", messageExt.getMsgId(), messageBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
