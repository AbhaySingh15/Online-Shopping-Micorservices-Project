package com.abhay.salesorderservice.controller;
import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SalesOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders")
    public ResponseEntity<SalesOrderResponseModel> createOrder(@RequestBody @Valid SalesOrderRequestModel orderRequestModel){
       return orderService.createOrder(orderRequestModel);

    }

    @GetMapping("/orders")
    public ResponseEntity<List<SalesOrder>> getAllOrdersOfACustomer(@RequestParam("customerId") Long cust_id){
        ResponseEntity<List<SalesOrder>> responseEntity= orderService.getOrderDetailsByCustomerId(cust_id);
        return responseEntity;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<SalesOrderResponseModel> getOrderDetailsByOrderId(@PathVariable Long orderId){
        return orderService.getOrderDetailsByOrderId(orderId);
    }
}
