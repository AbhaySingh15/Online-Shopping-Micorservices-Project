package com.abhay.customerservice.controller;

import com.abhay.customerservice.entity.Customer;
import com.abhay.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
       return customerService.getAllCustomerDetails();
    }

    @PostMapping("/customer")
    public Customer saveCustomer(@Valid @RequestBody Customer customerToBeSaved){
       return customerService.saveCustomer(customerToBeSaved);
    }

}