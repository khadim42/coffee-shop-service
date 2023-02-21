package com.kh.coffeeshopservice.service;

import com.kh.coffeeshopservice.converter.DtoConverter;
import com.kh.coffeeshopservice.dto.OrderDto;
import com.kh.coffeeshopservice.model.Customer;
import com.kh.coffeeshopservice.model.OrderInfo;
import com.kh.coffeeshopservice.model.Queue;
import com.kh.coffeeshopservice.repository.CustomerRepository;
import com.kh.coffeeshopservice.repository.OrderRepository;
import com.kh.coffeeshopservice.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    final OrderRepository orderRepository;

    final QueueRepository queueRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository, QueueRepository queueRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.queueRepository = queueRepository;
    }

    public Customer registerUser(Customer customer){

        return customerRepository.save(customer);
    }

    public OrderDto placeOrder(OrderDto orderDto) throws Exception {

        OrderInfo orderInfo = DtoConverter.toOrderInfo(orderDto);
        orderInfo.setStatus(OrderInfo.Status.NOT_ATTEMPTED);
        orderInfo.setOrderTime(System.currentTimeMillis());
        orderInfo.setCustomer(customerRepository.findById(orderDto.getCustomerId()).get());

        Queue queue = updateQueueWhereQueueHasLessCustomers(orderInfo.getRestaurant().getId());

        orderInfo.setQueueId(queue.getId());

        orderInfo.setTotalPrice(orderInfo.getFoodItems().stream().mapToDouble(e -> e.getPrice() ).sum());

        return DtoConverter.toOrderDto(orderRepository.save(orderInfo));
    }


    @Transactional
    public Queue updateQueueWhereQueueHasLessCustomers(Long restaurantId) throws Exception {
        Queue queue = queueRepository.findFirstByRestaurantIdOrderByCurrentNoAsc(restaurantId);
        if(queue.getCurrentNo()<queue.getSize()){
            queue.setCurrentNo(queue.getCurrentNo()+1);
        }else{
            throw new Exception("Queue is full! please wait and try again . ");
        }
        return queueRepository.save(queue);
    }
    public List<OrderInfo> findOrdersByStatus(OrderInfo.Status status){
        return orderRepository.findAllByStatus(status);
    }


}
