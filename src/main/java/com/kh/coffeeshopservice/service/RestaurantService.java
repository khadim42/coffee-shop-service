package com.kh.coffeeshopservice.service;

import com.kh.coffeeshopservice.converter.DtoConverter;
import com.kh.coffeeshopservice.dto.CompletedCustomersScore;
import com.kh.coffeeshopservice.dto.CustomerDto;
import com.kh.coffeeshopservice.dto.OrderDto;
import com.kh.coffeeshopservice.model.Customer;
import com.kh.coffeeshopservice.model.OrderInfo;
import com.kh.coffeeshopservice.model.Queue;
import com.kh.coffeeshopservice.model.Restaurant;
import com.kh.coffeeshopservice.repository.CustomerRepository;
import com.kh.coffeeshopservice.repository.OrderRepository;
import com.kh.coffeeshopservice.repository.QueueRepository;
import com.kh.coffeeshopservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    final CustomerRepository customerRepository;

    final OrderRepository orderRepository;

    final
    RestaurantRepository restaurantRepository;

    final
    QueueRepository queueRepository;


    @Autowired
    public RestaurantService(CustomerRepository customerRepository, OrderRepository orderRepository, QueueRepository queueRepository, RestaurantRepository restaurantRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.queueRepository = queueRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<CustomerDto> getAllWaitingCustomer(Long restaurantId){

        List<OrderInfo> list = orderRepository.findAllByStatusAndRestaurantId(OrderInfo.Status.NOT_ATTEMPTED,restaurantId);
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for(OrderInfo orderInfo:list){
            customerDtoList.add(
                    new CustomerDto(orderInfo.getCustomer().getId(),
                            orderInfo.getCustomer().getUserName(),
                            orderInfo.getCustomer().getPhone(),orderInfo.getId()));
        }
        return customerDtoList;
    }

    public CompletedCustomersScore getAllCompletedCustomersWithScore(Long restaurantId){
        CompletedCustomersScore completedCustomersScore = new CompletedCustomersScore();

        List<OrderInfo> list = orderRepository.findAllByStatusAndRestaurantId(OrderInfo.Status.SUCCESS,restaurantId);
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for(OrderInfo orderInfo:list){
            customerDtoList.add(
                    new CustomerDto(orderInfo.getCustomer().getId(),
                            orderInfo.getCustomer().getUserName(),
                            orderInfo.getCustomer().getPhone(),orderInfo.getId()));
        }
        completedCustomersScore.setScore(list.size());
        completedCustomersScore.setCustomerDtoList(customerDtoList);
        return completedCustomersScore;
    }

    public Restaurant findById(Long restaurantId){
        return restaurantRepository.findById(restaurantId).get();
    }
    @Transactional
    public OrderDto completeOrder(OrderDto orderDto){
        OrderInfo orderInfo = orderRepository.getById(orderDto.getId());
        Queue queue = queueRepository.findById(orderInfo.getQueueId()).get();
        queue.setCurrentNo(queue.getCurrentNo()-1);
        queueRepository.save(queue);
        orderInfo.setStatus(OrderInfo.Status.SUCCESS);

        return DtoConverter.toOrderDto(orderRepository.save(orderInfo));
    }



}
