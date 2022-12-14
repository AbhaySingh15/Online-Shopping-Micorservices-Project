package com.abhay.salesorderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_Line_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JsonIgnore
    private int item_quantity;

    @ManyToOne
    @JsonIgnore
    private SalesOrder salesOrder;

    public Order_Line_Item(String name, int item_quantity,SalesOrder salesOrder){
        this.name = name;
        this.item_quantity = item_quantity;
        this.salesOrder = salesOrder;
    }
}
