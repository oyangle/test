package com.yangle.service.demotest.proxy;

/**
 * programname: product_factory
 * <p>
 * title: 实现类
 *
 * @author: yishao
 * <p>
 * created: 2018-11-15 11:12
 **/
public class RealObject implements Action{

    public void doSomeThing() {
        System.out.println("执行doSomeThing");
    }
}
