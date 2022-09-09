package com.abhay.orderlookupservice.controller;

import com.abhay.orderlookupservice.entity.SalesOrder;
import com.abhay.orderlookupservice.model.SalesOrderResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class OrderLookupController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order-lookup-service/orders")
    public ResponseEntity<SalesOrder[]> getOrderDetailsByCustomerId(@RequestParam("customerId") Long cust_id){
        ResponseEntity<SalesOrder[]> responseEntity = restTemplate.getForEntity("http://sales-order-service/orders?customerId="+cust_id,SalesOrder[].class);
        log.info(Arrays.toString(responseEntity.getBody()));
        return responseEntity;
    }

    @GetMapping("/order-lookup-service/orders/{orderId}")
    public ResponseEntity<SalesOrderResponseModel> getOrderDetailsByOrderId(@PathVariable Long orderId){
        ResponseEntity<SalesOrderResponseModel> responseEntity = restTemplate.getForEntity("http://sales-order-service/orders/"+orderId,SalesOrderResponseModel.class);
        log.info(responseEntity.getBody().toString());
        return responseEntity;
    }
}
