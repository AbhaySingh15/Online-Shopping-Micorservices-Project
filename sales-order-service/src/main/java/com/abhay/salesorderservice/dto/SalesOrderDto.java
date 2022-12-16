package com.abhay.salesorderservice.dto;

import com.abhay.salesorderservice.entity.Order_Line_Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class SalesOrderDto extends RepresentationModel<SalesOrderDto> {

    private String order_desc;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_date;
    private Long cust_id;
    @NotEmpty(message = "item list cannot be empty")
    private List<String> item_names;
    private Long id;
    private List<Order_Line_Item> order_line_itemList;

}
