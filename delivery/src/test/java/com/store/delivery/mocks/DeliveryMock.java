package com.store.delivery.mocks;

import com.store.delivery.models.dtos.delivery.DeliveryDTO;
import com.store.delivery.models.Delivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryMock {

    public static Delivery delivery() {
        return new Delivery(UUID.randomUUID(), LocalDate.now(),PurchaseMock.purchaseDTO());
    }

    public static DeliveryDTO deliveryDTO() {
        return new DeliveryDTO(LocalDate.now(),PurchaseMock.purchaseDTO());
    }

    public static List<Delivery> deliveryList() {
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery());
        return deliveries;
    }

    public static List<DeliveryDTO> deliveryDTOList() {
        List<DeliveryDTO> deliveries = new ArrayList<>();
        deliveries.add(deliveryDTO());
        return deliveries;
    }
}
