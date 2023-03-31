package com.store.delivery.mappers.delivery;

import com.store.delivery.dtos.delivery.DeliveryDTO;
import com.store.delivery.dtos.product.ProductDTO;
import com.store.delivery.dtos.purchase.PurchaseDTO;
import com.store.delivery.dtos.purchase.PurchaseDeliveryDTO;
import com.store.delivery.models.Delivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryMapper {

    public static DeliveryDTO fromEntity(Delivery delivery) {
        return new DeliveryDTO(
                delivery.getDate(),
                delivery.getPurchase()
        );
    }

    public static Delivery fromPurchaseDTO(PurchaseDTO purchaseDTO){
        return new Delivery(
                UUID.randomUUID(),
                LocalDate.now(),
                purchaseDTO
        );
    }

    public static List<DeliveryDTO> fromListEntity(List<Delivery> deliveries) {
        List<DeliveryDTO> deliveriesDTO = new ArrayList<>();
        deliveries.forEach(delivery -> {
            deliveriesDTO.add(
                    new DeliveryDTO(delivery.getDate(), delivery.getPurchase())
            );
        });
        return deliveriesDTO;

    }

    public static PurchaseDeliveryDTO fromPurchaseDTOtoPucharseDelvieryDTO(PurchaseDTO purchaseDTO) {
        return new PurchaseDeliveryDTO(
                new ProductDTO(
                        purchaseDTO.getProductId(),
                        purchaseDTO.getQuantity()
                )
        );
    }
}
