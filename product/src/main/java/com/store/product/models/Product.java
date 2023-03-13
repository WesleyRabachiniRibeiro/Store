package com.store.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_PRODUCT")
@SequenceGenerator(name = "SQ_PRODUCT", sequenceName = "SQ_PRODUCT", allocationSize = 1)
public class Product {

    @Id
    @Column(name = "CD_PRODUCT", unique = true, nullable = false)
    @GeneratedValue(generator = "SQ_PRODUCT", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NM_PRODUCT", nullable = false)
    private String name;

    @Column(name = "QT_PRODUCT")
    private Integer quantity;

    @Column(name = "VL_PRODUCT", nullable = false, scale = 2)
    private BigDecimal value;

    @Column(name = "DS_PRODUCT", length = 100)
    private String description;

    @Column(name = "DS_ACTIVE")
    private boolean isActive;
}
