package com.example.wkredis.wk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Description
 * <br> Created by WangKun on 2019/05/09
 * <br>
 **/
@Service
@CacheConfig(cacheNames = "DEMO:SEV")
public class DemoService {

    @Autowired
    RedisTemplate redisTemplate;

    @Cacheable
    public Student get() {
        return new Student("违法发放", 10);
    }

    @Cacheable
    public Student set(int age) {
        return new Student("违法发放", age);
    }
}
