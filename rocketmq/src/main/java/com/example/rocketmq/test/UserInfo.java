package com.example.rocketmq.test;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-27
 * @desc
 **/
@Data
public class UserInfo implements Serializable {
    private Long id;
    private String name;
    private String password;
    private List<String> roles;
}
