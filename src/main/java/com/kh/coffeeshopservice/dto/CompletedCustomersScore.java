package com.kh.coffeeshopservice.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CompletedCustomersScore {

    private int score;

    private List<CustomerDto> customerDtoList;
}
