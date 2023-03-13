package com.store.purchase.mappers;

import com.store.purchase.dtos.product.ProductByUserDTO;
import com.store.purchase.dtos.product.ProductDTO;
import com.store.purchase.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.dtos.purchase.PurchaseDTO;
import com.store.purchase.dtos.purchase.TotalValueDTO;
import com.store.purchase.models.Purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class PurchaseMapper {

    public static Purchase fromNewPurchase(PurchaseDTO purchaseDTO, ProductDTO productDTO) {
        return new Purchase(
                UUID.randomUUID().toString(),
                purchaseDTO.getUserId(),
                purchaseDTO.getProductId(),
                purchaseDTO.getQuantity(),
                productDTO.getValue(),
                productDTO.getValue().multiply(BigDecimal.valueOf(purchaseDTO.getQuantity()))
        );
    }

    public static List<PurchaseByUserDTO> productDTOListAndPurchaseDTOListToPurchaseByUserDTOList(List<Purchase> purchaseList, List<ProductDTO> productList) {
        List<PurchaseByUserDTO> purchaseByUserDTOList = new ArrayList<>();
        IntStream.range(0, purchaseList.toArray().length).forEach(idx -> {
            purchaseByUserDTOList.add(
                    new PurchaseByUserDTO(
                        new ProductByUserDTO(
                                productList.get(idx).getName(),
                                purchaseList.get(idx).getQuantity(),
                                purchaseList.get(idx).getValue()
                        ),
                        new TotalValueDTO(
                                purchaseList.get(idx).getTotalValue()
                        )
                    )
            );
        });
        return purchaseByUserDTOList;
    }

    public static PurchaseByUserDTO productDTOAndPurchaseDTOToPurchaseByUserDTO(Purchase purchase, ProductDTO product) {
        return new PurchaseByUserDTO(
                new ProductByUserDTO(
                        product.getName(),
                        purchase.getQuantity(),
                        purchase.getValue()
                ),
                new TotalValueDTO(
                        purchase.getTotalValue()
                )
        );
    }
}
