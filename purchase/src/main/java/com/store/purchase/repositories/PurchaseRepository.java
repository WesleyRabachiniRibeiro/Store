package com.store.purchase.repositories;

import com.store.purchase.models.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, UUID>{

    Optional<List<Purchase>> findByUserId(Long userId);

    @Query("{'userId' : ?0, '_id' : ?1}")
    Optional<Purchase> findByUserIdAndPurchaseId(@Param("userId") Long userId, @Param("id") String id);
}
