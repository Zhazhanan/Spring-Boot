package com.example.mybatisdemo.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * demo
 *
 * @author dell
 * @date 2019-01-19 14:09:15
 */
@Data
public class DemoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Short age;

    private Boolean sex;

    private Date birthday;

    private String address;

    private Timestamp createTime;

    private Boolean valid;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DemoDTO{");
        sb.append("  id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", address=").append(address);
        sb.append(", createTime=").append(createTime);
        sb.append(", valid=").append(valid);
        return sb.toString();
    }
}