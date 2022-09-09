package com.abhay.customerservice.service.impl;

import com.abhay.customerservice.CustomerServiceApplication;
import com.abhay.customerservice.entity.Customer;
import com.abhay.customerservice.repository.CustomerRepository;
import com.abhay.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerRepo.getAllCustomers();
    }

    @Override
    public Customer saveCustomer(Customer customerToBeSaved) {
        return customerRepo.save(customerToBeSaved);
    }
}
