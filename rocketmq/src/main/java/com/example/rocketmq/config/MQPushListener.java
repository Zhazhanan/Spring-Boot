package com.example.rocketmq.config;

import com.alibaba.fastjson.JSONObject;
import com.example.rocketmq.test.UserInfo;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

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
                byte[] body = messageExt.getBody();
                Map object = JSONObject.parseObject(body, Map.class);
                UserInfo userInfo = JSONObject.parseObject(body, UserInfo.class);
                LOGGER.info("consumeMessage::message = {}", userInfo.toString());
                LOGGER.info("consumeMessage::message = {}", object.toString());
            }
        } catch (Exception e) {
            LOGGER.error("consumeMessage::e = {} ", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
