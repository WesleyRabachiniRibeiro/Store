package com.store.delivery.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.delivery.models.dtos.delivery.DeliveryDTO;
import com.store.delivery.mocks.DeliveryMock;
import com.store.delivery.mocks.PurchaseMock;
import com.store.delivery.models.Delivery;
import com.store.delivery.repositories.DeliveryRepository;
import com.store.delivery.services.impl.DeliveryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeliveryServiceTest {

    @InjectMocks
    private DeliveryServiceImpl service;

    @Mock
    private DeliveryRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private KafkaService kafkaService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service,"objectMapper",objectMapper);
    }

    @Test
    public void shouldSaveDelivery() throws JsonProcessingException {
        doNothing().when(kafkaService).send(anyString());
        doNothing().when(repository).save(any(Delivery.class));
        String message = objectMapper.writeValueAsString(PurchaseMock.purchaseDTO());
        service.save(message);
    }

    @Test
    public void shouldFindAll(){
        when(repository.findAll()).thenReturn(Optional.of(DeliveryMock.deliveryList()));
        List<Delivery> deliveries = service.findAll();
        assertEquals(deliveries.get(0).getPurchase().getUserId(), DeliveryMock.deliveryList().get(0).getPurchase().getUserId());
    }

    @Test
    public void shouldFindAllByUser(){
        when(repository.findByUserId(anyLong())).thenReturn(Optional.of(DeliveryMock.deliveryList()));
        List<DeliveryDTO> deliveries = service.findAllByUser(1L);
        assertNotNull(deliveries);
    }

    @Test
    public void shouldFindAllByUserAndDelivery(){
        when(repository.findByUserAndDelivery(anyLong(), anyString())).thenReturn(Optional.of(DeliveryMock.delivery()));
        DeliveryDTO delivery = service.findAllByUserAndDelivery(1L,"1");
        assertNotNull(delivery);
    }
}
