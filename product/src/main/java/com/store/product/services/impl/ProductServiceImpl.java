package com.store.product.services.impl;

import com.store.product.exception.StatusException;
import com.store.product.dtos.ProductDTO;
import com.store.product.mappers.ProductMapper;
import com.store.product.models.Product;
import com.store.product.repositories.ProductRepository;
import com.store.product.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

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
}
