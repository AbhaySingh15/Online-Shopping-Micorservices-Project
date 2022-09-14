package com.abhay.salesorderservice.controller;
import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.model.SalesOrderUpdateModel;
import com.abhay.salesorderservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SalesOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody @Valid SalesOrderRequestModel orderRequestModel) {
        return orderService.createOrder(orderRequestModel);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrdersOfACustomer(@RequestParam("customerId") Long cust_id) {
        ResponseEntity<?> responseEntity = orderService.getOrderDetailsByCustomerId(cust_id);
        return responseEntity;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        return orderService.getOrderDetailsByOrderId(orderId);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody SalesOrderUpdateModel salesOrderUpdateModel) {
        return orderService.updateOrder(orderId,salesOrderUpdateModel);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<?> deleteOrderByOrderId(@PathVariable Long orderId){
        return orderService.deleteOrderByOrderId(orderId);
    }

}
