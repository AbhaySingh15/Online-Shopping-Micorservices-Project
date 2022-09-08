package com.abhay.orderlookupservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_Line_Item {

    private Long id;
    private String name;
    private int item_quantity;
    @JsonIgnore
    private SalesOrder salesOrder;

}
