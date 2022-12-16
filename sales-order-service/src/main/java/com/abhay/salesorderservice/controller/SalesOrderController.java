package com.abhay.salesorderservice.controller;

import com.abhay.salesorderservice.dto.SalesOrderDto;
import com.abhay.salesorderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SalesOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody @Valid SalesOrderDto salesOrderDto) {
        return orderService.createOrder(salesOrderDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrdersOfACustomer(@RequestParam("customerId") Long cust_id) {
//        ResponseEntity<?> allOrdersOfACustomer = getAllOrdersOfACustomer(cust_id);
//        for(SalesOrderDto salesOrderDto: allOrdersOfACustomer){
//            Link selfLink = linkTo(SalesOrderController.class).slash("/orders?").slash("customerId="+cust_id).withSelfRel();
//            salesOrderDto.add(selfLink);
//        }
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
