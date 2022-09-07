package com.abhay.salesorderservice.service;

import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<SalesOrderResponseModel> createOrder(SalesOrderRequestModel orderRequestModel);
    ResponseEntity<SalesOrder> getOrderDetailsByCustomerId(Long cust_id);
    ResponseEntity<SalesOrderResponseModel> getOrderDetailsByOrderId(Long order_id);

}
