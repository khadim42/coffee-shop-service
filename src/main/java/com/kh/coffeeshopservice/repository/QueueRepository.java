package com.kh.coffeeshopservice.repository;

import com.kh.coffeeshopservice.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue,Long> {

    List<Queue> findByRestaurantId(Long restaurantId);

    Queue findFirstByRestaurantIdOrderByCurrentNoAsc(Long restaurantId);
}
