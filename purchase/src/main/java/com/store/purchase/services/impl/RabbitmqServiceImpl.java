package com.store.purchase.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.purchase.services.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String queueName, Object message)  {
        try {
            String messageJson = objectMapper.writeValueAsString(message);
            this.template.convertAndSend(queueName, messageJson);
        }catch (Exception err) {
            err.printStackTrace();
        }
    }
}
