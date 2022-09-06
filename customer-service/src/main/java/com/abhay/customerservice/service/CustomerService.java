package com.abhay.customerservice.service;

import com.abhay.customerservice.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomerDetails();
    Customer saveCustomer(Customer customerToBeSaved);
}
