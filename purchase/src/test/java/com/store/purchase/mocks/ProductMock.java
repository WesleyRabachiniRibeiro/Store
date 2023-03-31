package com.store.purchase.mocks;

import com.store.purchase.dtos.product.ProductDTO;
import com.store.purchase.models.DataPresenter;

import java.math.BigDecimal;
import java.util.List;

public class ProductMock {
    public static DataPresenter<ProductDTO> product() {
        return new DataPresenter(new ProductDTO(1L,"Kiwi",20, BigDecimal.ONE,"A beautiful kiwi"));
    }
}
