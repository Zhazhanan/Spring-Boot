package com.example.wkredis;

import com.alibaba.fastjson.JSON;
import com.example.wkredis.config.RedisDistributedLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WkRedisApplicationTests {

    @Autowired
    RedisDistributedLock redisDistributedLock;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    class Student {
        public String name;
        public int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Test
    public void test194737() {
        Student student = new Student("违法发放", 10);
        stringRedisTemplate.opsForValue().set("违法发放key3", JSON.toJSONString(student));
    }

    @Test
    public void test20068() {
        Student student = new Student("违法发放", 10);
//        redisTemplate.opsForValue().set("测试key",student);
        redisTemplate.opsForValue().set("测试key3333",student);
    }

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

