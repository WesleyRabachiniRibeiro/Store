package com.store.delivery.models.dtos.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.delivery.models.dtos.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDeliveryDTO {

    @JsonProperty("purchase")
    private ProductDTO purchaseDataDTO;
}
