package com.store.delivery.controllers;

import com.store.delivery.mocks.DeliveryMock;
import com.store.delivery.services.DeliveryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeliveryControllerTest {

    @InjectMocks
    private DeliveryController controller;

    @Mock
    private DeliveryService service;


    @Test
    void shouldFindAllByUser() {
        when(service.findAllByUser(anyLong())).thenReturn(DeliveryMock.deliveryDTOList());
        var response = controller.getAllByUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindAllDeliveryByUser() {
        when(service.findAllByUserAndDelivery(anyLong(), anyString())).thenReturn(DeliveryMock.deliveryDTO());
        var response = controller.getAllDeliveriesByUser(1L,"1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindAllDelivery() {
        when(service.findAll()).thenReturn(DeliveryMock.deliveryList());
        var response = controller.getAllDeliveries();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
