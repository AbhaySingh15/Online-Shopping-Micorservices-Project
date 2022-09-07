package com.abhay.salesorderservice.controller;
import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders")
    public ResponseEntity<SalesOrderResponseModel> createOrder(@RequestBody SalesOrderRequestModel orderRequestModel){
       return orderService.createOrder(orderRequestModel);

    }

    @GetMapping("/orders")
    public ResponseEntity<SalesOrder> getAllOrdersOfACustomer(@RequestParam Long cust_id){
        return orderService.getOrderDetailsByCustomerId(cust_id);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<SalesOrderResponseModel> getOrderDetailsByOrderId(@Param("orderId") Long order_id){
        return orderService.getOrderDetailsByOrderId(order_id);
    }
}
