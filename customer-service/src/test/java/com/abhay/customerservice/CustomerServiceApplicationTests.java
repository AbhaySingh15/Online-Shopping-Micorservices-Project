package com.abhay.customerservice;

import com.abhay.customerservice.entity.Customer;
import com.abhay.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Test
    void contextLoads() {
    }

    @Test
    @BeforeAll
    public void createCustomer(){
        Customer customer = new Customer();
        customer.setEmail("johndoe@email.com");
        customer.setFirst_name("john");
        customer.setLast_name("doe");

        Customer customer2 = new Customer();
        customer.setEmail("janedoe@email.com");
        customer.setFirst_name("jane");
        customer.setLast_name("doe");
      List<Customer> customerList = customerRepository.saveAll(List.of(customer,customer2));
      assertTrue(customerList.size()>0);
    }

    @Test
    public void getAllCustomers(){
       List<Customer> customerList = customerRepository.findAll();
       assertTrue(customerList.size()>0);
    }

}
