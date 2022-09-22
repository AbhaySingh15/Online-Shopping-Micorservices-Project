package com.abhay.salesorderservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Item {

    private Long id;
    private String name;
    private String description;
    private double price;
}