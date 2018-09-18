package com.yangle.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * programname: product_factory
 * <p>
 * title: 测试基类
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 18:11
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseTest {

    @Resource
    protected ApplicationContext applicationContext;

    @Test
    public void test(){

    }

}
