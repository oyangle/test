package com.yangle.service.web;

import com.yangle.service.biz.redis.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * programname: product_factory
 * <p>
 *
 * title:分布式锁实现 秒杀购票
 *
 * @author: yishao
 * <p>
 * created: 2018-12-17 17:05
 **/
@Controller
public class CurrentKillTickets {

    @Resource
    private RedisService redisService;

    int n =500;

    //启动成功测试
    @RequestMapping("/redistest")
    @ResponseBody
    public String status(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){

        for (int i = 0; i < 50; i++) {

            Runs s = new Runs();
            Thread t = new Thread(s);
            t.start();

        }

        return "好的";
    }

    public class Runs implements Runnable{

        @Override
        public void run() {

            while (true) {
                String uuid = UUID.randomUUID().toString();
                boolean sale = redisService.tryLock("sale", uuid, 1000);

                if (sale) {
                    if (n < 1) {
                        redisService.releaseLock("sale",uuid);
                        System.out.println("当前票余量为："+n);
                        return;
                    }

                    //执行逻辑部分
                    System.out.println(Thread.currentThread().getName() + "获得了锁");
                    System.out.println(--n);

                    redisService.releaseLock("sale",uuid);

                }
            }

        }
    }

}
