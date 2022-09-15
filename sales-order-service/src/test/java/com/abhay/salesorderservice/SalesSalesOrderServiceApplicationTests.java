package com.abhay.salesorderservice;

import com.abhay.salesorderservice.controller.SalesOrderController;
import com.abhay.salesorderservice.entity.CustomerSOS;
import com.abhay.salesorderservice.entity.Item;
import com.abhay.salesorderservice.entity.Order_Line_Item;
import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.repository.Customer_SOS_Repository;
import com.abhay.salesorderservice.repository.OrderRepository;
import com.abhay.salesorderservice.repository.Order_line_Item_Repository;
import com.abhay.salesorderservice.service.OrderService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class SalesSalesOrderServiceApplicationTests {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SalesOrderController controller;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order_line_Item_Repository order_line_item_repository;
    @Autowired
    private Customer_SOS_Repository customer_sos_repository;
    @Test
    void contextLoads() {
    }

    public SalesOrder getSalesOrderObject(){
        // creating sales order and setting its fields
        SalesOrder salesOrder = new SalesOrder();
        CustomerSOS customerSOS = new CustomerSOS();

        customerSOS.setCust_email("test@email.com");
        customerSOS.setCust_first_name("test firstname");
        customerSOS.setCust_last_name("test lastname");
        customer_sos_repository.save(customerSOS);

        salesOrder.setOrder_desc("this is a test order");
        salesOrder.setOrder_date(new Date());
        salesOrder.setCustomer_sos(customerSOS);

        List<Order_Line_Item> order_line_itemList = new ArrayList<>();
        order_line_itemList.add(new Order_Line_Item("laptop",2,salesOrder));
        order_line_itemList.add(new Order_Line_Item("monitor",1,salesOrder));
        salesOrder.setOrder_line_itemList(order_line_itemList);
        return salesOrder;
    }

    @Test
    @BeforeAll
    public void testCreateOrder(){
       SalesOrder salesOrder = orderRepository.save(getSalesOrderObject());
       List<Order_Line_Item> order_line_itemList = order_line_item_repository.saveAll(salesOrder.getOrder_line_itemList());
       assertEquals("this is a test order",salesOrder.getOrder_desc());
       assertEquals(salesOrder.getId(),order_line_itemList.get(0).getSalesOrder().getId());
        log.info(salesOrder.getOrder_desc());
        log.info(salesOrder.getId().toString());
    }


}
