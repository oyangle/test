package com.yangle.service.dao.entity;

/**
 * programname: product_factory
 * <p>
 * title: 用户表
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 17:00
 **/
public class ProductUser extends BaseEntity{

    private String userName;
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
