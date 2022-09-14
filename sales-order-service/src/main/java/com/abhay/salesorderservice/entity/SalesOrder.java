package com.abhay.salesorderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_date;
    private String order_desc;
    @JsonIgnore
    private double total_price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private CustomerSOS customer_sos;

}
