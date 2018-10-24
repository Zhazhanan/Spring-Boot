package com.example.swaggerbootstrapuidemo;

import com.example.swaggerbootstrapuidemo.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class SwaggerBootstrapUiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerBootstrapUiDemoApplication.class, args);
    }
}
