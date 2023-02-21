package com.kh.coffeeshopservice;


import com.kh.coffeeshopservice.dto.CompletedCustomersScore;
import com.kh.coffeeshopservice.dto.CustomerDto;
import com.kh.coffeeshopservice.dto.MenuItemDto;
import com.kh.coffeeshopservice.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestClient {
    private static final String API_BASE_URL = "http://localhost:8080";
    private static final Logger log = LoggerFactory.getLogger(RestClient.class);
    private static final RestTemplate restTemplate = new RestTemplate();

    private static final Long RESTAURANT_ID = 1L;



    private static void listAllWaitingCustomers() {
        log.info("-----List All Persons-----");
        CustomerDto[] allPersons = restTemplate.getForObject(API_BASE_URL + "/restaurant/orders/"+RESTAURANT_ID, CustomerDto[].class);
        if (allPersons != null && allPersons.length > 0) {
            for (CustomerDto person : allPersons) {
                log.info(person.toString());
            }
        } else {
            log.warn("No persons found");
        }
    }



    private static void listAllCompletedOrders() {
        log.info("-----List All Completed Orders and Score -----");
        CompletedCustomersScore completedCustomersScore = restTemplate.getForObject(API_BASE_URL + "/restaurant/orders/done/"+RESTAURANT_ID, CompletedCustomersScore.class);
        log.info(completedCustomersScore.toString());

    }

    private static Long createOrder(OrderDto order) {
        log.info("-----Create Order-----");
        ResponseEntity<OrderDto> responseEntity = restTemplate.postForEntity(API_BASE_URL + "/customer/order", order, OrderDto.class);
        log.info("Location: " + responseEntity.getHeaders().getLocation());
        log.info("ORDER CREATED: " + responseEntity.getBody() );
        return responseEntity.getBody().getId();
    }
    private static Long completeOrder(OrderDto order) {
        log.info("-----COMPLETE Order-----");
        ResponseEntity<OrderDto> responseEntity = restTemplate.postForEntity(API_BASE_URL + "/restaurant/order/complete", order, OrderDto.class);
        log.info("Location: " + responseEntity.getHeaders().getLocation());
        log.info("COMPLETED ORDER : " + responseEntity.getBody() );
        return responseEntity.getBody().getId();
    }

    public static void main(String[] args) {
        listAllCompletedOrders();
        listAllWaitingCustomers();


        OrderDto orderDto = new OrderDto();
        orderDto.setRestaurantId(RESTAURANT_ID);
        orderDto.setCustomerId(1L);

        List<MenuItemDto> menuItemDtoList = new ArrayList<>();
        MenuItemDto menuItemDto1 = new MenuItemDto();
        menuItemDto1.setId(1L);
        menuItemDto1.setName("tea1");
        menuItemDto1.setPrice(22);
        menuItemDtoList.add(menuItemDto1);

        MenuItemDto menuItemDto2 = new MenuItemDto();
        menuItemDto2.setId(2L);
        menuItemDto2.setName("coffee");
        menuItemDto2.setPrice(33);
        menuItemDtoList.add(menuItemDto2);
        orderDto.setMenuItemDtos(menuItemDtoList);

        Long orderId = createOrder(orderDto);
        orderDto.setId(orderId);
        listAllWaitingCustomers();
        listAllCompletedOrders();

        completeOrder(orderDto);
        listAllWaitingCustomers();
        listAllCompletedOrders();
    }
}
