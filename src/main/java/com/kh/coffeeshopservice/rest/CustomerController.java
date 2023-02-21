package com.kh.coffeeshopservice.rest;

import com.kh.coffeeshopservice.dto.OrderDto;
import com.kh.coffeeshopservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Place Order in queue by customer")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto){
        try {
            return new ResponseEntity<>(this.customerService.placeOrder(orderDto), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable To Place Order", HttpStatus.FORBIDDEN);
        }
    }


}
