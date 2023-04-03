package com.store.purchase.services;

import com.store.purchase.models.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.models.dtos.purchase.PurchaseDTO;

import java.util.List;

public interface PurchaseService {

    void purchase(PurchaseDTO dto);

    List<PurchaseByUserDTO> findPurchasesByUser(Long id);

    PurchaseByUserDTO findPurchaseOfUser(Long userId, String id);
}
