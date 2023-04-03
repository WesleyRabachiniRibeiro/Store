package com.store.delivery.services;

import com.store.delivery.models.dtos.delivery.DeliveryDTO;
import com.store.delivery.models.Delivery;

import java.util.List;

public interface DeliveryService {

    void save(String message);

    List<Delivery> findAll();

    List<DeliveryDTO> findAllByUser(Long useId);

    DeliveryDTO findAllByUserAndDelivery(Long useId, String deliveryId);
}
