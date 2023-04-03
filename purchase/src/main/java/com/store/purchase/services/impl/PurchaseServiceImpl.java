package com.store.purchase.services.impl;

import com.store.purchase.config.client.ProductFeign;
import com.store.purchase.config.rabbit.RabbitmqConstants;
import com.store.purchase.models.dtos.product.ProductDTO;
import com.store.purchase.models.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.models.dtos.purchase.PurchaseDTO;
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
        ProductDTO product = productFeign.searchProduct(dto.getProductId()).getData();
        if(product.getQuantity() >= dto.getQuantity()) {
            repository.save(PurchaseMapper.fromNewPurchase(dto, product));
            rabbitmqService.sendMessage(RabbitmqConstants.QUEUE_BUY, dto);
        }else {
            throw new RuntimeException("The quantity of the product is greater than in stock");
        }
    }

    @Override
    public List<PurchaseByUserDTO> findPurchasesByUser(Long id) {
        List<Purchase> purchaseList = repository.findByUserId(id).orElseThrow(() -> new NoResultException("No results found"));
        List<ProductDTO> productList = new ArrayList<>();
        purchaseList.forEach(purchase -> productList.add(productFeign.searchProduct(purchase.getProductId()).getData()));
        List<PurchaseByUserDTO> purchaseByUserDTO = PurchaseMapper.productDTOListAndPurchaseDTOListToPurchaseByUserDTOList(purchaseList, productList);
        return purchaseByUserDTO;
    }

    @Override
    public PurchaseByUserDTO findPurchaseOfUser(Long userId, String id) {
        Purchase purchase = repository.findByUserIdAndPurchaseId(userId, id).orElseThrow(() -> new NoResultException("No results found"));
        return PurchaseMapper.productDTOAndPurchaseDTOToPurchaseByUserDTO(purchase, productFeign.searchProduct(purchase.getProductId()).getData());
    }
}
