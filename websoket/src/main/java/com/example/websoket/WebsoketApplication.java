package com.example.websoket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebsoketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsoketApplication.class, args);
	}
}
