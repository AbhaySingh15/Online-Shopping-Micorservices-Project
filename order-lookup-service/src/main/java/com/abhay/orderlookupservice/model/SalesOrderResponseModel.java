package com.abhay.orderlookupservice.model;


import com.abhay.orderlookupservice.entity.Order_Line_Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SalesOrderResponseModel {
    private Long id;
    private String order_desc;
    private List<Order_Line_Item> order_line_items;
}
