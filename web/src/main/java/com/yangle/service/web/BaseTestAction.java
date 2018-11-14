package com.yangle.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/status")
    @ResponseBody
    public String status(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        LOGGER.info("===================ddddd");
        return "SUCCESS";
    }

}
