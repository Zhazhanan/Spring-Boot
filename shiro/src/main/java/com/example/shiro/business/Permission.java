package com.example.shiro.business;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author WangKun
 * @create 2018-08-27
 * @desc
 **/
@Data
@Entity
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String permission;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
