package com.store.delivery.repositories;

import com.store.delivery.constants.RedisConstants;
import com.store.delivery.models.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class DeliveryRepository {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Delivery> template;

    public void save(Delivery delivery) {
        template.opsForHash().put(RedisConstants.HASH_DELIVERY, delivery.getId(), delivery);
    }

    public Optional<List<Delivery>> findAll() {
        List<Object> deliveriesObject = template.opsForHash().values(RedisConstants.HASH_DELIVERY);
        Optional<List<Delivery>> deliveries = Optional.of(deliveriesObject.stream()
                                        .map(delivery -> (Delivery) delivery)
                                            .collect(Collectors.toList()));
        return deliveries;
    }

    public Optional<List<Delivery>> findByUserId(Long userId) {
        try {
            List<Object> deliveriesObject = template.opsForHash().values(RedisConstants.HASH_DELIVERY);
            List<Delivery> deliveries = deliveriesObject.stream()
                    .map(obj -> (Delivery) obj)
                    .filter(delivery -> delivery.getPurchase().getUserId().longValue() == userId)
                    .collect(Collectors.toList());
            return Optional.of(deliveries);
        } catch (Exception err) {
            return Optional.empty();
        }
    }

    public Optional<Delivery> findByUserAndDelivery(Long userId, String deliveryId){
        try {
            List<Object> deliveriesObject = template.opsForHash().values(RedisConstants.HASH_DELIVERY);
            Optional<Delivery> response = deliveriesObject.stream()
                    .map(obj -> (Delivery) obj)
                    .filter(delivery -> delivery.getPurchase().getUserId().longValue() == userId && delivery.getId().toString().equals(deliveryId))
                    .findFirst();
            return response;
        } catch (Exception err) {
            return Optional.empty();
        }
    }
}
