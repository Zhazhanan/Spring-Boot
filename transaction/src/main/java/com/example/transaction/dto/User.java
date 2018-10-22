package com.example.transaction.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WangKun
 * @create 2018-07-23 下午 2:19
 * @desc
 **/
public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
