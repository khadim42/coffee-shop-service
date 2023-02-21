package com.kh.coffeeshopservice.repository;

import com.kh.coffeeshopservice.model.Customer;
import com.kh.coffeeshopservice.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findAllByStatus(OrderInfo.Status status);

    List<OrderInfo> findAllByStatusAndRestaurantId(OrderInfo.Status status, Long restaurantId);


}
