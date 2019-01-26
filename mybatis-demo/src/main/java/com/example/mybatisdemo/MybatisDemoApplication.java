package com.example.mybatisdemo;

import com.example.mybatisdemo.demo.domain.City;
import com.example.mybatisdemo.demo.domain.DemoDTO;
import com.example.mybatisdemo.demo.mapper.CityMapper;
import com.example.mybatisdemo.demo.mapper.DemoDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    private final CityMapper cityMapper;
    private final DemoDAO demoDAO;

    public MybatisDemoApplication(CityMapper cityMapper, DemoDAO demoDAO) {
        this.cityMapper = cityMapper;
        this.demoDAO = demoDAO;
    }

    @Bean
    CommandLineRunner sampleCommandLineRunner() {
        City ca = cityMapper.findByState("CA");
        DemoDTO demoDTO = demoDAO.findById("1");
        return (args) -> System.out.println("------------------" + ca + "------------------" + demoDTO);
    }
}

