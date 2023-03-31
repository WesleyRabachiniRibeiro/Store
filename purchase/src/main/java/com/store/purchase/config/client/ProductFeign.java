package com.store.purchase.config.client;

import com.store.purchase.dtos.product.ProductDTO;

import com.store.purchase.models.DataPresenter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products", url = "http://localhost:8081/v1/products")
public interface ProductFeign {

    @GetMapping
    DataPresenter<List<ProductDTO>> productList();

    @GetMapping("/{id}")
    DataPresenter<ProductDTO> searchProduct(@PathVariable Long id);
}
