package com.store.product.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.product.mocks.ProductMock;
import com.store.product.models.Product;
import com.store.product.repositories.ProductRepository;
import com.store.product.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service,"objectMapper", objectMapper);
    }

    @Test
    public void shouldSaveTheProduct() throws Exception {
        when(repository.save(any(Product.class))).thenReturn(any(Product.class));
        service.saveProduct(ProductMock.registryProduct());
        verify(repository).save(any(Product.class));
    }

    @Test
    public void shouldActivateProduct() {
        Product product = ProductMock.deactivateProduct();
        when(repository.save(product)).thenReturn(product);
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        service.activeProduct(1l);
        assertTrue(product.isActive());
        verify(repository).save(any(Product.class));
    }

    @Test
    public void shouldDeactivateProduct() {
        Product product = ProductMock.activeProduct();
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        service.deactivateProduct(1l);
        assertFalse(product.isActive());
        verify(repository).save(any(Product.class));
    }

    @Test
    public void shouldReturnProduct() {
        when(repository.findById(1L)).thenReturn(Optional.of(ProductMock.activeProduct()));
        Product product = service.findProduct(1L);
        assertNotNull(product);
        verify(repository).findById(1L);
    }

    @Test
    public void shouldReturnAllActiveProduct() {
        when(repository.findAllActiveProducts()).thenReturn(ProductMock.productList());
        List<Product> response = service.findAllActiveProducts();
        assertNotNull(response);
    }

    @Test
    public void shouldUpdateQuantity() throws Exception {
        Product product = ProductMock.activeProduct();
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);
        String message = objectMapper.writeValueAsString(ProductMock.purchaseProductDTO());
        service.updateQuantity(message);
        verify(repository).save(any(Product.class));
    }
}
