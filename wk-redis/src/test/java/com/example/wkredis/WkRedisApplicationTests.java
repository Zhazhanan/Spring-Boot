package com.example.wkredis;

import com.example.wkredis.config.RedisDistributedLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WkRedisApplicationTests {

    @Autowired
    RedisDistributedLock redisDistributedLock;

    /**
     * @describe: 测试获取分布式锁
     * @author: WangKun
     * @date: 2019-02-26 02:56
     **/
    @Test
    public void contextLoads() {
        Object lock = redisDistributedLock.lock("key3", "val2", 100);
        System.out.println(lock);
    }

    /**
     * @describe: 测试释放分布式锁
     * @author: WangKun
     * @date: 2019-02-26 02:56
     **/
    @Test
    public void test143947() {
        Object o = redisDistributedLock.releaseLock("key3", "val2");
        System.out.println(o);
    }


}

