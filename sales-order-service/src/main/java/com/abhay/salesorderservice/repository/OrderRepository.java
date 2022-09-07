package com.abhay.salesorderservice.repository;

import com.abhay.salesorderservice.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<SalesOrder,Long> {
}
