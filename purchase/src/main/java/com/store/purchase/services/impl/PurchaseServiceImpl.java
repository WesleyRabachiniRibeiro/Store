package com.store.purchase.services.impl;

import com.store.purchase.client.ProductFeign;
import com.store.purchase.config.connections.Constants;
import com.store.purchase.dtos.product.ProductDTO;
import com.store.purchase.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.dtos.purchase.PurchaseDTO;
import com.store.purchase.mappers.PurchaseMapper;
import com.store.purchase.models.Purchase;
import com.store.purchase.repositories.PurchaseRepository;
import com.store.purchase.services.PurchaseService;
import com.store.purchase.services.RabbitmqService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private RabbitmqService rabbitmqService;

    @Override
    public void purchase(PurchaseDTO dto) {
        Purchase purchase = PurchaseMapper.fromNewPurchase(dto, productFeign.searchProduct(dto.getProductId()));
        rabbitmqService.sendMessage(Constants.QUEUE_BUY, purchase);
        repository.save(purchase);
    }

    @Override
    public List<PurchaseByUserDTO> findPurchasesByUser(Long id) {
        List<Purchase> purchaseDTOList = repository.findByUserId(id).orElseThrow(() -> new NoResultException("No results found"));
        List<ProductDTO> productList = new ArrayList<>();
        purchaseDTOList.forEach(purchase -> productList.add(productFeign.searchProduct(purchase.getProductId())));
        List<PurchaseByUserDTO> purchaseByUserDTO = PurchaseMapper.productDTOListAndPurchaseDTOListToPurchaseByUserDTOList(purchaseDTOList, productList);
        return purchaseByUserDTO;
    }

    @Override
    public PurchaseByUserDTO findPurchaseOfUser(Long userId, String id) {
        Purchase purchase = repository.findByUserIdAndPurchaseId(userId, id).orElseThrow(() -> new NoResultException("No results found"));
        return PurchaseMapper.productDTOAndPurchaseDTOToPurchaseByUserDTO(purchase, productFeign.searchProduct(purchase.getProductId()));
    }
}
