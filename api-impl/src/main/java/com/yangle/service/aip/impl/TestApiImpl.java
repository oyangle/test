package com.yangle.service.aip.impl;

import com.yangle.service.api.TestApi;
import org.springframework.stereotype.Service;

/**
 * programname: product_factory
 * <p>
 * title: 11
 *
 * @author: yishao
 * <p>
 * created: 2018-12-10 21:26
 **/
@Service
public class TestApiImpl implements TestApi{
    public String sayHello() {
        return "HELLO";
    }
}
