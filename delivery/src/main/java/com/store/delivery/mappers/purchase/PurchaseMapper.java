package com.store.delivery.mappers.purchase;

import com.store.delivery.models.dtos.purchase.PurchaseDTO;
import com.store.delivery.models.dtos.product.ProductDTO;
import com.store.delivery.models.dtos.purchase.PurchaseDeliveryDTO;

public class PurchaseMapper {

    public static PurchaseDeliveryDTO fromPurchaseDTO(PurchaseDTO purchaseDTO) {
        return new PurchaseDeliveryDTO(
                new ProductDTO(
                    purchaseDTO.getProductId(),
                    purchaseDTO.getQuantity()
                )
        );
    }
}
