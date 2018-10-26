package com.example.rocketmq;

import com.example.rocketmq.config.bean.MQConsumer;
import com.example.rocketmq.config.bean.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketmqApplicationTests {

    @Autowired
    MQProducer rocketMQProducer;
    @Autowired
    MQConsumer rocketMQConsumer;

    @Test
    public void contextLoads() {
    }

}
