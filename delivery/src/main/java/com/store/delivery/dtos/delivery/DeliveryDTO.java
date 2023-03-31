package com.store.delivery.dtos.delivery;

import com.store.delivery.dtos.purchase.PurchaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    private LocalDate date;

    private PurchaseDTO purchase;
}
