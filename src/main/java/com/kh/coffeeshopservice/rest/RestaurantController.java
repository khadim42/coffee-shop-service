package com.kh.coffeeshopservice.rest;

import com.kh.coffeeshopservice.dto.CompletedCustomersScore;
import com.kh.coffeeshopservice.dto.OrderDto;
import com.kh.coffeeshopservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/order/complete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Serve Order and Mark Completed")
    public ResponseEntity<?> completeOrder(@RequestBody OrderDto orderDto){

        return new ResponseEntity<>(this.restaurantService.completeOrder(orderDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiting/{rid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Orders in waiting")
    public ResponseEntity<?> getAllOrdersWaiting(@PathVariable Long rid){

        return new ResponseEntity<>(this.restaurantService.getAllWaitingCustomer(rid), HttpStatus.OK);
    }


    @RequestMapping(value = "/done/{rid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Orders in Completed")
    public ResponseEntity<?> getAllDoneOrders(@PathVariable Long rid){

        return new ResponseEntity<>(this.restaurantService.getAllCompletedCustomersWithScore(rid), HttpStatus.OK);
    }
}
