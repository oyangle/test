package com.yangle.service.demotest;

import com.yangle.service.demotest.proxy.Action;
import com.yangle.service.demotest.proxy.DynamicProxyTest;
import com.yangle.service.demotest.proxy.RealObject;

import java.lang.reflect.Proxy;

/**
 * programname: product_factory
 * <p>
 * title: 11
 *
 * @author: yishao
 * <p>
 * created: 2018-11-15 10:59
 **/
public class MainTest {

    public static void main(String[] args) {

        RealObject realObject = new RealObject();
        Action action = (Action)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[]{Action.class}, new DynamicProxyTest(realObject));

        realObject.doSomeThing();

        System.out.println("=====================");
        action.doSomeThing();

    }

}
