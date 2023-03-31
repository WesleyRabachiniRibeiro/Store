package com.store.delivery.mappers.purchase;

import com.store.delivery.dtos.purchase.PurchaseDTO;
import com.store.delivery.dtos.product.ProductDTO;
import com.store.delivery.dtos.purchase.PurchaseDeliveryDTO;

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
