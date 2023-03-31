package com.store.delivery.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.delivery.constants.RabbitmqConstants;
import com.store.delivery.dtos.delivery.DeliveryDTO;
import com.store.delivery.dtos.purchase.PurchaseDTO;
import com.store.delivery.mappers.delivery.DeliveryMapper;
import com.store.delivery.models.Delivery;
import com.store.delivery.repositories.DeliveryRepository;
import com.store.delivery.services.DeliveryService;
import com.store.delivery.services.KafkaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @RabbitListener(queues = RabbitmqConstants.QUEUE_BUY)
    public void save(@Payload String message) {
        try{
            PurchaseDTO purchase = objectMapper.readValue(message, PurchaseDTO.class);
            kafkaService.send(objectMapper.writeValueAsString(DeliveryMapper.fromPurchaseDTOtoPucharseDelvieryDTO(purchase)));
            repository.save(DeliveryMapper.fromPurchaseDTO(purchase));
        }catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Override
    public List<Delivery> findAll() {
        return repository.findAll().orElseThrow(() -> new RuntimeException("No deliveries found!"));
    }

    @Override
    public List<DeliveryDTO> findAllByUser(Long useId) {
        List<Delivery> deliveries = repository.findByUserId(useId).orElseThrow(() -> new RuntimeException("No deliveries found!"));
        return DeliveryMapper.fromListEntity(deliveries);
    }

    @Override
    public DeliveryDTO findAllByUserAndDelivery(Long useId, String deliveryId) {
        Delivery delivery = repository.findByUserAndDelivery(useId, deliveryId).orElseThrow(() -> new RuntimeException("No deliveries found!"));
        return DeliveryMapper.fromEntity(delivery);
    }
}
