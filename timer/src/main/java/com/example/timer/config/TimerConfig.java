package com.example.timer.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Description
 * <br> Created by WangKun on 2019/06/20
 * <br> 计时器配置类
 **/
@Configuration
public class TimerConfig {
    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(2,
                new BasicThreadFactory.Builder().namingPattern("jy-timer-pool-%d").daemon(true).build());
    }
}
