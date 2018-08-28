package com.example.jwt.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WangKun
 * @create 2018-08-27
 * @desc
 **/
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer password;
}
