package com.abhay.salesorderservice.service;

import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    SalesOrderResponseModel createOrder(SalesOrderRequestModel orderRequestModel);

    ResponseEntity getOrderDetails(Long cust_id);


}
