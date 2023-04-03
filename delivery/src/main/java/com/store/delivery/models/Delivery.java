package com.store.delivery.models;

import com.store.delivery.constants.RedisConstants;
import com.store.delivery.models.dtos.purchase.PurchaseDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(RedisConstants.HASH_DELIVERY)
public class Delivery implements Serializable {

    @Id
    private UUID id;

    private LocalDate date;

    private PurchaseDTO purchase;

}
