package com.example.mybatisdemo;

import com.example.mybatisdemo.demo.domain.City;
import com.example.mybatisdemo.demo.mapper.CityMapper;
import com.example.mybatisdemo.demo.mapper.DemoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

    @Resource
    CityMapper cityMapper;
    @Resource
    DemoDAO demoDAO;

    @Test
    public void contextLoads() {
        City byState = cityMapper.findByState("CA");
        System.out.println(byState + "=============");

        System.out.println("---------"+demoDAO.findById("1"));
    }

}

