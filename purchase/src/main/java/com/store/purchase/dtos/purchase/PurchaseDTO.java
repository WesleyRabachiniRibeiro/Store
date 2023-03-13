package com.store.purchase.dtos.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
