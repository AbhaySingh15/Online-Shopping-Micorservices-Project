package com.abhay.salesorderservice.service;

import com.abhay.salesorderservice.model.SalesOrderDto;
import org.springframework.http.ResponseEntity;


public interface OrderService {

    ResponseEntity<?> createOrder(SalesOrderDto salesOrderDto);
    ResponseEntity<?> getOrderDetailsByCustomerId(Long cust_id);
    ResponseEntity<?> getOrderDetailsByOrderId(Long order_id);
    ResponseEntity<?> updateOrder(Long orderId, SalesOrderDto salesOrderDtoInput);

    ResponseEntity<?> deleteOrderByOrderId(Long orderId);
}
