package com.abhay.salesorderservice.repository;

import com.abhay.salesorderservice.entity.CustomerSOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_SOS_Repository extends JpaRepository<CustomerSOS,Long> {
}
