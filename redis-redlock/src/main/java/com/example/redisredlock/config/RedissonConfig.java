package com.example.redisredlock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangKun
 * @create 2018-11-30
 * @desc redisson配置可分为两种方式，1.yaml；2.javaconfig
 * redisson 支持单机，主从，哨兵，集群等模式
 **/
@Configuration
public class RedissonConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonConfig.class);

    @Bean
    RedissonClient redissonSentinel() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.69.52:6388");
        LOGGER.info("redissonSentinel:: init ok");
        return Redisson.create(config);
    }

    //
//    @Bean
//    RedissonClient redissonSentinel() {
//        //此为哨兵模式
//        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName("master6388")
//                .addSentinelAddress("redis://127.0.0.1:26388");
////                .setPassword("123456");
//        return (Redisson) Redisson.create(config);
//    }


//    @Bean
//    public RedissonClient redisson() throws IOException {
//        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
//        Config config = Config.fromYAML(RedisRedlockApplication.class.getClassLoader().getResource("redisson.yaml"));
//        return Redisson.create(config);
//    }

}
