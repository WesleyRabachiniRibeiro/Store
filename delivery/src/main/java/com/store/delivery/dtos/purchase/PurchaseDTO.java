package com.store.delivery.dtos.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO implements Serializable {

    private Long userId;

    private Long productId;

    private Integer quantity;
}
