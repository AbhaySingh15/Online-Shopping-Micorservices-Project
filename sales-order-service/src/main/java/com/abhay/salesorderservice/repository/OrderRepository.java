package com.abhay.salesorderservice.repository;

import com.abhay.salesorderservice.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<SalesOrder,Long> {

    @Query(value = "select * from sales_order where customer_sos_cust_id = :#{#cust_id}",nativeQuery = true)
    List<SalesOrder> findByCustomerId(@Param("cust_id") Long cust_id);

}
