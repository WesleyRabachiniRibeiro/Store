package com.store.purchase.models.dtos.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.purchase.models.dtos.product.ProductByUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseByUserDTO {

    @JsonProperty("product")
    private ProductByUserDTO productByUserDTO;

    @JsonProperty("purchase")
    private TotalValueDTO totalValueDTO;
}
