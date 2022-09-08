package com.abhay.orderlookupservice.entity;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerSOS {

    private Long cust_id;
    private String cust_email;
    private String cust_first_name;
    private String cust_last_name;

}
