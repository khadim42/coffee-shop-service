package com.kh.coffeeshopservice.dto;

import lombok.Data;

@Data
public class MenuItemDto {

    private Long id;
    private String name;

    private double price;
}
