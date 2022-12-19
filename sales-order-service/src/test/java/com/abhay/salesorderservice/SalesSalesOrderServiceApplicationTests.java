package com.abhay.salesorderservice;

import com.abhay.salesorderservice.entity.CustomerSOS;
import com.abhay.salesorderservice.entity.Order_Line_Item;
import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.repository.Customer_SOS_Repository;
import com.abhay.salesorderservice.repository.OrderRepository;
import com.abhay.salesorderservice.repository.Order_line_Item_Repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class SalesSalesOrderServiceApplicationTests {

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
        //creating dummy sales order and setting its fields
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setOrder_desc("this is a test order");
        salesOrder.setOrder_date(new Date());

        List<Order_Line_Item> order_line_itemList = new ArrayList<>();
        order_line_itemList.add(new Order_Line_Item("laptop",1, salesOrder));
        order_line_itemList.add(new Order_Line_Item("monitor",1, salesOrder));
        salesOrder.setOrder_line_itemList(order_line_itemList);
        return salesOrder;
    }


    private CustomerSOS getCustomerObject() {
        //creating dummy customer
        CustomerSOS customerSOS = new CustomerSOS();
        customerSOS.setCust_email("dummy@email.com");
        customerSOS.setCust_first_name("dummy firstname");
        customerSOS.setCust_last_name("dummy lastname");
        return customerSOS;
    }

    @Test
    @BeforeAll
    public void testCreateOrder(){
        CustomerSOS customerSOS = getCustomerObject();
        SalesOrder salesOrder = getSalesOrderObject();
        salesOrder.setCustomer_sos(customerSOS);
        customer_sos_repository.save(customerSOS);

       SalesOrder savedSalesOrder = orderRepository.save(salesOrder);
       //List<Order_Line_Item> order_line_itemList = order_line_item_repository.saveAll(salesOrder.getOrder_line_itemList());
       List<Order_Line_Item> order_line_itemList = order_line_item_repository.findAll();
        assertEquals("this is a test order",savedSalesOrder.getOrder_desc());
       assertNotEquals(0,order_line_itemList.size());
        order_line_itemList.forEach(order_line_item -> System.out.println(order_line_item.getId()));

        log.info(savedSalesOrder.getOrder_desc());
        log.info(savedSalesOrder.getId().toString());
        log.info(savedSalesOrder.getCustomer_sos().getCust_id().toString());
        log.info(savedSalesOrder.getCustomer_sos().getCust_first_name());
    }

    @Test
    public void getOrderDetailsByOrderIdTest(){
        List<SalesOrder> salesOrder = orderRepository.findAll();
        assertDoesNotThrow(()->orderRepository.findById(salesOrder.get(0).getId()).get());
    }

    @Test
    public void getOrderDetailsByCustomerIdTest(){
        List<SalesOrder> salesOrder = orderRepository.findAll();
        List<SalesOrder> salesOrdersList = orderRepository.findByCustomerId(salesOrder.get(0).getCustomer_sos().getCust_id());
        assertFalse(CollectionUtils.isEmpty(salesOrdersList));
    }
}
