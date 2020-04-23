package com.example.rocketmq;

import com.example.rocketmq.config.MQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MQConfig.class)
public class  RocketmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }
}
