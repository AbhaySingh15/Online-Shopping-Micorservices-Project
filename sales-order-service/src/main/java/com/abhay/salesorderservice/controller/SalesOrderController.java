package com.abhay.salesorderservice.controller;

import com.abhay.salesorderservice.model.SalesOrderDto;
import com.abhay.salesorderservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class SalesOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody @Valid SalesOrderDto salesOrderDto) {
        return orderService.createOrder(salesOrderDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrdersOfACustomer(@RequestParam("customerId") Long cust_id) {
        return orderService.getOrderDetailsByCustomerId(cust_id);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        return orderService.getOrderDetailsByOrderId(orderId);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody SalesOrderDto salesOrderDtoInput) {
        return orderService.updateOrder(orderId,salesOrderDtoInput);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<?> deleteOrderByOrderId(@PathVariable Long orderId){
        return orderService.deleteOrderByOrderId(orderId);
    }

}
