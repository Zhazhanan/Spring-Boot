package com.example.swaggerbootstrapuidemo.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(value = "User", description = "用户信息描述")
@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    @ApiModelProperty("证件号")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value = "姓名",required = true)
    private String name;
    @ApiModelProperty("年龄")
    private int age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("家庭住址")
    private String address;
    @ApiModelProperty(value = "Email",hidden = true)
    private String email;
}
