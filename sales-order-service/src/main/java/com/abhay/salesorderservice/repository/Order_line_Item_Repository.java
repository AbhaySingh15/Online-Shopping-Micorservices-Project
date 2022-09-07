package com.abhay.salesorderservice.repository;

import com.abhay.salesorderservice.entity.Order_Line_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_line_Item_Repository extends JpaRepository<Order_Line_Item,Long> {
}
