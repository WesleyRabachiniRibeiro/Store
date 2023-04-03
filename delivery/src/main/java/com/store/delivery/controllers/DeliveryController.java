package com.store.delivery.controllers;

import com.store.delivery.models.DataPresenter;
import com.store.delivery.models.dtos.delivery.DeliveryDTO;
import com.store.delivery.models.Delivery;
import com.store.delivery.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @GetMapping
    public ResponseEntity<DataPresenter<List<Delivery>>> getAllDeliveries() {
        return ResponseEntity.ok(new DataPresenter(service.findAll()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DataPresenter<List<DeliveryDTO>>> getAllByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new DataPresenter(service.findAllByUser(userId)));
    }

    @GetMapping("/{userId}/{deliveryId}")
    public ResponseEntity<DataPresenter<DeliveryDTO>> getAllDeliveriesByUser(@PathVariable Long userId, @PathVariable String deliveryId) {
        return ResponseEntity.ok(new DataPresenter(service.findAllByUserAndDelivery(userId, deliveryId)));
    }
}
