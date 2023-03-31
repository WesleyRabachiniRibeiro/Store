package com.store.product.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.product.constants.KafkaConstants;
import com.store.product.dtos.purchase.PurchaseDTO;
import com.store.product.dtos.purchase.PurchaseProductDTO;
import com.store.product.exception.StatusException;
import com.store.product.dtos.product.ProductDTO;
import com.store.product.mappers.ProductMapper;
import com.store.product.models.Product;
import com.store.product.repositories.ProductRepository;
import com.store.product.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Product saveProduct(ProductDTO dto) {
        return repository.save(ProductMapper.fromEntity(dto));
    }

    @Override
    public void activeProduct(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        if (product.isActive()) {
            throw new StatusException(product.isActive());
        }
        product.setActive(true);
        repository.save(product);
    }

    @Override
    public void deactivateProduct(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        if (!product.isActive()) {
            throw new StatusException(product.isActive());
        }
        product.setActive(false);
        repository.save(product);
    }

    @Override
    public Product findProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> findAllActiveProducts() {
        return repository.findAllActiveProducts();
    }

    @Override
    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void updateQuantity(@Payload String message) throws Exception {
        try {
            PurchaseProductDTO purchase = objectMapper.readValue(message, PurchaseProductDTO.class);
            Product product = this.findProduct(purchase.getPurchase().getProductId());
            product.setQuantity(product.getQuantity() - purchase.getPurchase().getQuantity());
            repository.save(product);
        }catch (Exception e) {
            throw new Exception("Have a problem to read JSON");
        }
    }
}
