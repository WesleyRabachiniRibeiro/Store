package com.store.product.mocks;

import com.store.product.models.dtos.product.ProductDTO;
import com.store.product.models.dtos.purchase.PurchaseDTO;
import com.store.product.models.dtos.purchase.PurchaseProductDTO;
import com.store.product.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductMock {

    public static ProductDTO registryProduct() {
        return new ProductDTO("Kiwi",50, BigDecimal.ONE,"A Beautiful Kiwi");
    }

    public static Product activeProduct() {
        return new Product(1L,"Kiwi",50, BigDecimal.ONE,"A Beautiful Kiwi",true);
    }

    public static Product deactivateProduct() {
        return new Product(1L,"Kiwi",50, BigDecimal.ONE,"A Beautiful Kiwi",false);
    }

    public static List<Product> productList() {
        List<Product> productList = new ArrayList<>();
        productList.add(activeProduct());
        return productList;
    }
    public static PurchaseProductDTO purchaseProductDTO() {
        return new PurchaseProductDTO(new PurchaseDTO(1L,10));
    }
}
