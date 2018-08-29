package com.example.shiro.business;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-27
 * @desc
 **/
@Entity
@Data
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String password;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
//    private List<Role> roles;
}
