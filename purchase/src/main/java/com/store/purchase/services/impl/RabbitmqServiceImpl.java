package com.store.purchase.services.impl;

import com.store.purchase.services.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate template;

    public void sendMessage(String queueName, Object message) {
        this.template.convertAndSend(queueName, message);
    }
}
