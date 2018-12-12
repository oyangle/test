package com.yangle.service.biz.activemq;

import javax.jms.Destination;

/**
 * @program: product_factory
 * @description: 消息提供者
 * @author: yishao
 * @create: 2018-12-12 11:23
 **/
public interface ProducerService {

    /**
     * 发送消息
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination,String message);

}
