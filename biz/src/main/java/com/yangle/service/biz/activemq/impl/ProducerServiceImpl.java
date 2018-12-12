package com.yangle.service.biz.activemq.impl;

import com.yangle.service.biz.activemq.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * programname: product_factory
 * <p>
 * title: 消息提供者实现类
 *
 * @author: yishao
 * <p>
 * created: 2018-12-12 11:26
 **/
@Service
public class ProducerServiceImpl implements ProducerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final Destination destination,final String message) {

        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });

        LOGGER.info("发送消息成功："+message);

    }
}
