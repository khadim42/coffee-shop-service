package com.kh.coffeeshopservice.dto;


import com.kh.coffeeshopservice.model.OrderInfo;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private Long queueId;

    private Long restaurantId;

    private double totalPrice;

    private Long customerId;

    private List<MenuItemDto> menuItemDtos;
    private OrderInfo.Status status;

}
