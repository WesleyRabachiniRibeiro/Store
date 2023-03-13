package com.store.product.mappers;

import com.store.product.dtos.ProductDTO;
import com.store.product.models.Product;

public class ProductMapper {

    public static Product fromEntity(ProductDTO dto) {
        return new Product(
                null,
                dto.getName(),
                dto.getQuantity(),
                dto.getValue(),
                dto.getDescription(),
                true
        );
    }
}
