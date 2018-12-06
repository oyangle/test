package com.yangle.service.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * programname: product_factory
 * <p>
 * title: 首个调度任务
 *
 * @author: yishao
 * <p>
 * created: 2018-11-29 16:06
 **/
@Component
public class HelloWorldJob implements Job{

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("this is a info!");
        logger.error("this is a error");

        logger.info("=======this webroot.app "+System.getProperty("pf.root"));

    }
}
