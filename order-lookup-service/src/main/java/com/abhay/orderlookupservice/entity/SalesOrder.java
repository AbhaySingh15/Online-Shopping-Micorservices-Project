package com.abhay.orderlookupservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_date;
    private String order_desc;
    @JsonIgnore
    private double total_price;
    @JsonIgnore
    private CustomerSOS customer_sos;

}
