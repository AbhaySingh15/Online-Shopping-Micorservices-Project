package com.abhay.salesorderservice.controller;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.service.OrderService;
import com.abhay.salesorderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody SalesOrderRequestModel orderRequestModel){
        orderService.createOrder(orderRequestModel);
        return ResponseEntity.ok(null);
    }
}
