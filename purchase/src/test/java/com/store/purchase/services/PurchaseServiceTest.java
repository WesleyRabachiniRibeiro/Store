package com.store.purchase.services;

import com.store.purchase.config.client.ProductFeign;
import com.store.purchase.models.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.mappers.PurchaseMapper;
import com.store.purchase.mocks.ProductMock;
import com.store.purchase.mocks.PurchaseMock;
import com.store.purchase.models.Purchase;
import com.store.purchase.repositories.PurchaseRepository;
import com.store.purchase.services.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseServiceImpl service;

    @Mock
    private PurchaseRepository repository;

    @Mock
    private ProductFeign feign;

    @Mock
    private RabbitmqService rabbitmqService;

    @Test
    public void shouldMakeAPurchase() {
        when(repository.save(any(Purchase.class))).thenReturn(PurchaseMock.purchase());
        doNothing().when(rabbitmqService).sendMessage(anyString(), any());
        when(feign.searchProduct(anyLong())).thenReturn(ProductMock.product());
        service.purchase(PurchaseMock.purchaseDto());
        verify(repository).save(any(Purchase.class));
    }

    @Test
    public void shouldFindPurchasesByUser() {
        when(repository.findByUserIdAndPurchaseId(anyLong(),anyString())).thenReturn(Optional.of(PurchaseMock.purchase()));
        when(feign.searchProduct(anyLong())).thenReturn(ProductMock.product());
        PurchaseByUserDTO response = service.findPurchaseOfUser(anyLong(), anyString());
        PurchaseByUserDTO expected = PurchaseMapper.productDTOAndPurchaseDTOToPurchaseByUserDTO(PurchaseMock.purchase(), ProductMock.product().getData());
        assertEquals(expected.getProductByUserDTO().getName(), response.getProductByUserDTO().getName());
    }

    @Test
    public void shouldReturnPurchasesByUser() {
        when(repository.findByUserId(anyLong())).thenReturn(Optional.of(PurchaseMock.purchaseList()));
        when(feign.searchProduct(anyLong())).thenReturn(ProductMock.product());
        List<PurchaseByUserDTO> response = service.findPurchasesByUser(anyLong());
        assertNotNull(response.get(0));
    }
}
