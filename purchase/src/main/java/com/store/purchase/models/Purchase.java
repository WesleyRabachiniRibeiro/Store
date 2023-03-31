package com.store.purchase.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("purchase")
public class Purchase {

    @Id
    private String uuid;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal value;
    private BigDecimal totalValue;
}
