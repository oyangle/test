package com.yangle.web.test;

import com.alibaba.fastjson.JSONObject;
import com.yangle.service.biz.ProductBiz;
import com.yangle.service.dao.entity.ProductUser;
import com.yangle.service.service.ProductUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * programname: product_factory
 * <p>
 * title: mapper
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 19:23
 **/
public class MapperTest extends BaseTest{

    @Autowired
    private ProductUserService productUserService;
    @Autowired
    private ProductBiz productBiz;

    @Test
    public void testInsert1(){

        ProductUser user = new ProductUser();
        user.setUserName("张三");
        user.setAge(10);

        productUserService.insert(user);

    }

    @Test
    public void testQuery(){

        ProductUser productUser = productUserService.queryById(1L);
        System.out.println(JSONObject.toJSONString(productUser));

    }

    @Test
    public void testTx(){

        productBiz.addNewProduct();

    }

}
