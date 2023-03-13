package com.store.purchase.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductByUserDTO {

    private String name;

    @JsonProperty("qtd")
    private Integer quantity;

    private BigDecimal value;
}
