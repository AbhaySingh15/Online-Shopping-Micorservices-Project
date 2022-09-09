package com.abhay.salesorderservice.service;

import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<SalesOrderResponseModel> createOrder(SalesOrderRequestModel orderRequestModel);
    ResponseEntity<List<SalesOrder>> getOrderDetailsByCustomerId(Long cust_id);
    ResponseEntity<SalesOrderResponseModel> getOrderDetailsByOrderId(Long order_id);

}
