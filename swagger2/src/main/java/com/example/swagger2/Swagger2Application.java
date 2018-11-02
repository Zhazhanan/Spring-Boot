package com.example.swagger2;

import com.example.swagger2.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class Swagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(Swagger2Application.class, args);
    }
}
