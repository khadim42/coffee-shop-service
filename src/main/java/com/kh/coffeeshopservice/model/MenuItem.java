package com.kh.coffeeshopservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Restaurant restaurant;

    private String name;

    private String description;

    private double price;


    @ManyToMany
    private List<OrderInfo> orderInfoList;

}
