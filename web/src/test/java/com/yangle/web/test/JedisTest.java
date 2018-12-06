package com.yangle.web.test;

import com.yangle.service.biz.redis.RedisService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * programname: product_factory
 * <p>
 * title: eee
 *
 * @author: yishao
 * <p>
 * created: 2018-12-06 18:29
 **/
public class JedisTest extends BaseTest{

    @Resource
    private RedisService redisService;

    @Test
    public void test1(){

        if (redisService!=null){
            logger.info("创建成果");
        }else {
            logger.info("创建失败");
        }

        redisService.setV("test2","1111",0);


    }

}
