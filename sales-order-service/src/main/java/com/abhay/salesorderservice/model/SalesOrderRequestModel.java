package com.abhay.salesorderservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SalesOrderRequestModel {

    private String order_desc;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate order_date;
    private Long cust_id;
    @NotEmpty(message = "item list cannot be empty or null")
    private List<String> item_names;
}
