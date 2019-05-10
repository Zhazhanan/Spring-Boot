package com.example.wkredis.wk;

import java.io.Serializable;

/**
 * Description
 * <br> Created by WangKun on 2019/05/09
 * <br>
 **/
public class Student implements Serializable {
    private static final long serialVersionUID = -4066139418339796476L;


    public String name;
    public int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
