package com.store.purchase.mocks;

import com.store.purchase.dtos.product.ProductByUserDTO;
import com.store.purchase.dtos.product.ProductDTO;
import com.store.purchase.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.dtos.purchase.PurchaseDTO;
import com.store.purchase.dtos.purchase.TotalValueDTO;
import com.store.purchase.models.Purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PurchaseMock {
    public static PurchaseDTO purchaseDto() {
        return new PurchaseDTO(1L,1L,10);
    }

    public static Purchase purchase() {
        return new Purchase(UUID.randomUUID().toString(),1L,1L,10, BigDecimal.ONE, BigDecimal.TEN);
    }

    public static List<Purchase> purchaseList() {
        Purchase purchase = new Purchase(UUID.randomUUID().toString(),1L,1L,10, BigDecimal.ONE, BigDecimal.TEN);
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase);
        return purchases;
    }

    public static PurchaseByUserDTO purchaseByUserDTO() {
        ProductByUserDTO product = new ProductByUserDTO("kiwi",20,BigDecimal.ONE);
        PurchaseByUserDTO purchaseByUserDTO = new PurchaseByUserDTO(product, new TotalValueDTO(BigDecimal.TEN));
        return purchaseByUserDTO;
    }
}
