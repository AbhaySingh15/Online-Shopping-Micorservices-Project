package com.abhay.salesorderservice.service;

import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.model.SalesOrderUpdateModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<?> createOrder(SalesOrderRequestModel orderRequestModel);
    ResponseEntity<?> getOrderDetailsByCustomerId(Long cust_id);
    ResponseEntity<?> getOrderDetailsByOrderId(Long order_id);
    ResponseEntity<?> updateOrder(Long orderId, SalesOrderUpdateModel salesOrderUpdateModel);

//    ResponseEntity<?> deleteOrderByOrderId(Long orderId);
}
