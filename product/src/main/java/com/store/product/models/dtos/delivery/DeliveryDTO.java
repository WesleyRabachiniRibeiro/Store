package com.store.product.models.dtos.delivery;

import com.store.product.models.dtos.purchase.PurchaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private PurchaseDTO purchase;
}
