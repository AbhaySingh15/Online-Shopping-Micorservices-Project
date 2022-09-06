package com.abhay.customerservice.repository;

import com.abhay.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "Select * from Customer", nativeQuery = true)
    List<Customer> getAllCustomers();
}
