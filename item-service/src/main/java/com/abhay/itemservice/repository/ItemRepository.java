package com.abhay.itemservice.repository;

import com.abhay.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query(value = "select * from Item",nativeQuery = true)
    List<Item> getAllItems();

//    @Query(value = "select * from Item where name = :#{#name}",nativeQuery = true)
//    Item findByName(@Param("name") String name);

    Item findByName(String name);

}
