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
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserInfo userInfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<Permission> permissions;
}
