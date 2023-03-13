package com.store.purchase.services;

public interface RabbitmqService {

    void sendMessage(String queueName, Object message);
}
