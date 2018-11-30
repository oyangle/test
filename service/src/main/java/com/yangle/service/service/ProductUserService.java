package com.yangle.service.service;

import com.yangle.service.dao.entity.ProductUser;

import java.util.List;

/**
 * @program: product_factory
 * @description: 用户表服务
 * @author: yishao
 * @create: 2018-09-18 21:18
 **/
public interface ProductUserService {

    void insert(ProductUser user);

    ProductUser queryById(Long id);

    List<ProductUser> queryByName(String name);

}
