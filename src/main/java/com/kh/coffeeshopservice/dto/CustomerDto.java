package com.kh.coffeeshopservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String userName;

    private String phone;

    private Long orderId;
}
