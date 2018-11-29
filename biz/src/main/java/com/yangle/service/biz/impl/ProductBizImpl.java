package com.yangle.service.biz.impl;

import com.yangle.service.biz.ProductBiz;
import com.yangle.service.dao.entity.ProductUser;
import com.yangle.service.service.ProductUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * programname: product_factory
 * <p>
 * title: 产品化服务实现
 *
 * @author: yishao
 * <p>
 * created: 2018-11-14 12:09
 **/
@Service
public class ProductBizImpl implements ProductBiz{

    @Resource
    private ProductUserService productUserService;

    public void addNewProduct() {

        ProductUser user = new ProductUser();
        user.setAge(10);
        user.setUserName("李四");

        productUserService.insert(user);
    }

    public void throwE(){
        throw new RuntimeException("出异常啦");
    }

    @Transactional
    public void addNewProductRollBack() {
        ProductUser user = new ProductUser();
        user.setAge(10);
        user.setUserName("李四");

        productUserService.insert(user);
        throwE();
    }
}
