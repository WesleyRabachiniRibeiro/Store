package com.store.product.services;

import com.store.product.models.dtos.product.ProductDTO;
import com.store.product.models.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(ProductDTO dto);
    void activeProduct(Long id);
    void deactivateProduct(Long id);
    Product findProduct(Long id);
    List<Product> findAllActiveProducts();
    void updateQuantity(String message) throws Exception;
}
