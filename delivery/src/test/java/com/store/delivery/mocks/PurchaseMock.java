package com.store.delivery.mocks;

import com.store.delivery.dtos.purchase.PurchaseDTO;

public class PurchaseMock {

    public static PurchaseDTO purchaseDTO() {
        return new PurchaseDTO(1L,1L,10);
    }
}
