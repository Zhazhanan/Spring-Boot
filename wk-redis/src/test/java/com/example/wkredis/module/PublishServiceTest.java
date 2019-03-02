package com.example.wkredis.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishServiceTest {

    @Autowired
    private PublishService publishService;

    /**
     * @describe: 消息发布测试
     * @author: WangKun
     * @date: 2019-03-01 04:57
     **/
    @Test
    public void test() {
        for (int i = 1; i <= 10; i++) {
            //向dj主题里发布10个消息
            publishService.publish("dj", "like " + i + " 次");
        }
    }
}