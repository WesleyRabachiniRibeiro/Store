package com.store.purchase.controllers;

import com.store.purchase.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.dtos.purchase.PurchaseDTO;
import com.store.purchase.mocks.PurchaseMock;
import com.store.purchase.services.PurchaseService;
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
public class PurchaseControllerTest {

    @InjectMocks
    private PurchaseController controller;

    @Mock
    private PurchaseService service;

    @Test
    void shouldPurchase() {
        doNothing().when(service).purchase(any(PurchaseDTO.class));
        var response = controller.buy(PurchaseMock.purchaseDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindPurchasesByUser() {
        when(service.findPurchasesByUser(anyLong())).thenReturn(any());
        var response = controller.findPurchasesByUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindPurchaseOfUser() {
        when(service.findPurchaseOfUser(anyLong(), anyString())).thenReturn(PurchaseMock.purchaseByUserDTO());
        var response = controller.findPurchaseOfUser(1L,"1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
