package com.yangle.service.web;

import com.provider.service.api.FirstDemoApi;
import com.yangle.service.biz.activemq.ProducerService;
import com.yangle.service.biz.redis.RedisService;
import com.yangle.service.biz.testbiz.ProductBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * programname: product_factory
 * <p>
 * title: 测试controller
 *
 * @author: yishao
 * <p>
 * created: 2018-11-14 15:45
 **/

@Controller
public class BaseTestAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestAction.class);

    @Resource(name = "queueDestination1")
    Destination destination1;

    @Resource(name = "queueDestination2")
    Destination destination2;

    @Resource
    private ProductBiz productBiz;
    @Resource
    private RedisService redisService;
    @Resource
    private FirstDemoApi firstDemoApi;
    @Resource
    private ProducerService producerService;

    //启动成功测试
    @RequestMapping("/status")
    @ResponseBody
    public String status(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================status");
        return "SUCCESS";
    }

    // RPC调用测试
    @RequestMapping("/sayhello")
    @ResponseBody
    public String sayhello(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================sayhello");
        return firstDemoApi.queryOK().getDesc();
    }

    //操作DB测试
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================insert");
        productBiz.addNewProduct();

        return "SUCCESS";
    }

    //操作DB回滚测试
    @RequestMapping("/rollback")
    @ResponseBody
    public String rollback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================insert-rollback");
        productBiz.addNewProductRollBack();

        return "SUCCESS";
    }

    //Redis新增测试
    @RequestMapping("/redis/add")
    @ResponseBody
    public String redisAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){

        String key = request.getParameter("key");
        String value = request.getParameter("value");
        LOGGER.info("=================redis==add,key={},value={}",key,value);

        redisService.setV(key,value,0);

        return "SUCCESS";
    }

    //Redis获取值测试
    @RequestMapping("/redis/get")
    @ResponseBody
    public String redisGet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        String key = request.getParameter("key");
        LOGGER.info("=================redis==add,key={}",key);

        Object v = redisService.getV(key, 0);

        if(v!=null){
            return (String)v;
        }

        return "查无数据";
    }

    //发送消息测试
    @RequestMapping("/mq/send1")
    @ResponseBody
    public String sendMq1(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){

        LOGGER.info("=================send mq1!");

        for (int i=0;i<1;i++){
            producerService.sendMessage(destination1,"中文" + i);
        }

        return "send success";
    }

    //发送消息测试
    @RequestMapping("/mq/send2")
    @ResponseBody
    public String sendMq2(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){

        LOGGER.info("=================send mq2!");

        for (int i=0;i<100;i++){
            producerService.sendMessage(destination2,"destination2 test " + i);
        }

        return "send success";
    }


}
