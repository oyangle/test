package com.yangle.service.web;

import com.provider.service.api.FirstDemoApi;
import com.yangle.service.biz.ProductBiz;
import com.yangle.service.biz.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @Resource
    private ProductBiz productBiz;

    @Resource
    private RedisService redisService;

    @Resource
    private FirstDemoApi firstDemoApi;

    @RequestMapping("/status")
    @ResponseBody
    public String status(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================status");
        return "SUCCESS";
    }
    @RequestMapping("/sayhello")
    @ResponseBody
    public String sayhello(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================sayhello");
        return firstDemoApi.queryOK();
    }

    @RequestMapping("/rollback")
    @ResponseBody
    public String rollback(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================insert");
        productBiz.addNewProductRollBack();

        return "SUCCESS";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================insert");
        productBiz.addNewProduct();

        return "SUCCESS";
    }

    @RequestMapping("/redis/add")
    @ResponseBody
    public String redisAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){

        String key = request.getParameter("key");
        String value = request.getParameter("value");
        LOGGER.info("=================redis==add,key={},value={}",key,value);

        redisService.setV(key,value,0);

        return "SUCCESS";
    }
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


}
