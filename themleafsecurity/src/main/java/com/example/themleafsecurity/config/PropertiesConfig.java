package com.example.themleafsecurity.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author WangKun
 * @create 2018-07-31
 * @desc
 **/
@Data
public class PropertiesConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

}
