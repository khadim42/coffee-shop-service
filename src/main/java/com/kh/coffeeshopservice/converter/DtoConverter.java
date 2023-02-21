package com.kh.coffeeshopservice.converter;

import com.kh.coffeeshopservice.dto.CustomerDto;
import com.kh.coffeeshopservice.dto.MenuItemDto;
import com.kh.coffeeshopservice.dto.OrderDto;
import com.kh.coffeeshopservice.model.Customer;
import com.kh.coffeeshopservice.model.MenuItem;
import com.kh.coffeeshopservice.model.OrderInfo;
import com.kh.coffeeshopservice.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {



    public static OrderDto toOrderDto(OrderInfo orderInfo){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderInfo.getId());
        List<MenuItemDto> menuItemDtoList = new ArrayList<>();
        for(MenuItem menuItem:orderInfo.getFoodItems()){
            MenuItemDto menuItemDto = new MenuItemDto();
            menuItemDto.setId(menuItem.getId());
            menuItemDto.setName(menuItem.getName());
            menuItemDto.setPrice(menuItem.getPrice());
            menuItemDtoList.add(menuItemDto);
        }
        orderDto.setMenuItemDtos(menuItemDtoList);
        orderDto.setStatus(orderInfo.getStatus());
        orderDto.setCustomerId(orderInfo.getCustomer().getId());
        orderDto.setQueueId(orderInfo.getQueueId());
        orderDto.setTotalPrice(orderInfo.getTotalPrice());
        orderDto.setRestaurantId(orderInfo.getRestaurant().getId());

        return orderDto;
    }


    public static OrderInfo toOrderInfo(OrderDto orderDto){

        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setId(orderInfo.getId());
        List<MenuItem> menuItemList = new ArrayList<>();
        for(MenuItemDto menuItemDto:orderDto.getMenuItemDtos()){
            MenuItem menuItem = new MenuItem();
            menuItem.setId(menuItemDto.getId());
            menuItem.setPrice(menuItemDto.getPrice());
            menuItemList.add(menuItem);
        }
        orderInfo.setFoodItems(menuItemList);
        orderInfo.setStatus(orderDto.getStatus());
        Customer customer = new Customer();
        customer.setId(orderDto.getCustomerId());
        orderInfo.setCustomer(customer);
        orderInfo.setQueueId(orderDto.getQueueId());
        orderInfo.setTotalPrice(orderDto.getTotalPrice());
        Restaurant restaurant = new Restaurant();
        restaurant.setId(orderDto.getRestaurantId());
        orderInfo.setRestaurant(restaurant);

        return orderInfo;
    }
}
