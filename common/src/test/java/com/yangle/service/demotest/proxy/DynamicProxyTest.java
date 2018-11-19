package com.yangle.service.demotest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * programname: product_factory
 * <p>
 * title: 动态代理
 *
 * @author: yishao
 * <p>
 * created: 2018-11-15 10:59
 **/
public class DynamicProxyTest implements InvocationHandler{

    private Object object;

    public DynamicProxyTest(Object realObj){
        this.object = realObj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("这里是扩展逻辑");
        Object result = method.invoke(object, args);
        System.out.println("这里是结束的逻辑");

        return result;
    }
}
