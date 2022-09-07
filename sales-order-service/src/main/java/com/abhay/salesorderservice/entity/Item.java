package com.abhay.salesorderservice.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
public class Item {

    private Long id;
    private String name;
    private String description;
    private double price;
}