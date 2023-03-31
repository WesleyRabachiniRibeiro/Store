package com.store.delivery.services.impl;

import com.store.delivery.constants.KafkaConstants;
import com.store.delivery.services.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String message) {
        try {
            this.kafkaTemplate.send(KafkaConstants.PRODUCT, message);
        }catch (Exception err) {
            err.getStackTrace();
        }
    }
}
