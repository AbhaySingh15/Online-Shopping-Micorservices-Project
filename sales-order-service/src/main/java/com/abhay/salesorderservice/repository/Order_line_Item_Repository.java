package com.abhay.salesorderservice.repository;

import com.abhay.salesorderservice.entity.Order_Line_Item;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_line_Item_Repository extends JpaRepository<Order_Line_Item,Long> {

    @Query(value = "select * from order_line_item where sales_order_id = :#{#order_id}",nativeQuery = true)
    List<Order_Line_Item> getOrder_Line_ItemsByOrderId(@Param("order_id") Long order_id);

    @Modifying
    @Query(value = "delete from order_line_item where sales_order_id = :#{#orderId}",nativeQuery = true)
    void deleteOrder_Line_ItemsByOrderId(@Param("orderId") Long orderId);

//    Long deleteOrder_Line_ItemByOrderId(Long orderId);
}
