package com.store.product.controllers;

import com.store.product.dtos.product.ProductDTO;
import com.store.product.mocks.ProductMock;
import com.store.product.models.Product;
import com.store.product.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;

    @Test
    void shouldSaveProduct() {
        when(service.saveProduct(any(ProductDTO.class))).thenReturn(any(Product.class));
        var response = controller.saveProduct(ProductMock.registryProduct());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldChangeProductToActive() {
        doNothing().when(service).activeProduct(anyLong());
        var response = controller.activeProduct(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldChangeProductToDeactivate() {
        doNothing().when(service).deactivateProduct(anyLong());
        var response = controller.deactivateProduct(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindProductByUser() {
        when(service.findProduct(anyLong())).thenReturn(any(Product.class));
        var response = controller.searchProduct(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindPurchasesByUser() {
        when(service.findAllActiveProducts()).thenReturn(ProductMock.productList());
        var response = controller.productList();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
