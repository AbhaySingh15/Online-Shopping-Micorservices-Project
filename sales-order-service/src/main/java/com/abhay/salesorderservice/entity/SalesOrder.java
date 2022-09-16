package com.abhay.salesorderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_date;
    private String order_desc;
    @JsonIgnore
    private double total_price;

    @ManyToOne
    @JsonIgnore
    private CustomerSOS customer_sos;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "salesOrder")
    @JsonIgnore
    private List<Order_Line_Item> order_line_itemList;

}
