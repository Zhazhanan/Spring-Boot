package com.example.redisredlock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRedlockApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRedlockApplicationTests.class);

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    String lockKey = "testRedisson";//分布式锁的key

    @Test
    public void test165515() {
        // 设置一个key，aaa商品的库存数量为100
        stringRedisTemplate.opsForValue().set("aaa", "100");
        Assert.assertEquals("100", stringRedisTemplate.opsForValue().get("aaa"));
    }


    @Test
    public void testDistributed() {
        //执行的业务代码
        for (int i = 0; i < 55; i++) {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("aaa").toString());
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("aaa", (stock - 1) + "");
                System.out.println("test2_:lockkey:" + lockKey + ",stock:" + (stock - 1) + "");
            }
            lock.unlock(); //释放锁
        }
    }

    @Test
    public void testDistributed2() {
        //执行的业务代码
        for (int i = 0; i < 55; i++) {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("aaa").toString());
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("aaa", (stock - 1) + "");
                System.out.println("test2_:lockkey:" + lockKey + ",stock:" + (stock - 1) + "");
            }
            lock.unlock(); //释放锁
        }
    }

}
