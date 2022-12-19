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

    private SalesOrder getSalesOrderObject(){
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
    public void testCreateOrder(){
        SalesOrder savedSalesOrder = saveSalesOrderInTestDB();
        //List<Order_Line_Item> order_line_itemList = order_line_item_repository.saveAll(salesOrder.getOrder_line_itemList());
       List<Order_Line_Item> order_line_itemList = order_line_item_repository.findAll();
        assertEquals("this is a test order",savedSalesOrder.getOrder_desc());
       assertNotEquals(0,order_line_itemList.size());
       orderRepository.delete(savedSalesOrder);
    }

    private SalesOrder saveSalesOrderInTestDB() {
        CustomerSOS customerSOS = getCustomerObject();
        SalesOrder salesOrder = getSalesOrderObject();
        salesOrder.setCustomer_sos(customerSOS);
        customer_sos_repository.save(customerSOS);

        SalesOrder savedSalesOrder = orderRepository.save(salesOrder);
        return savedSalesOrder;
    }

//    @Test
//    public void getOrderDetailsByOrderIdTest(){
//        SalesOrder savedSalesOrder = saveSalesOrderInTestDB();
//        assertDoesNotThrow(()->orderRepository.findById(savedSalesOrder.getId()).get());
//        orderRepository.delete(savedSalesOrder);
//    }

    @Test
    public void getOrderDetailsByCustomerIdTest(){
        SalesOrder savedSalesOrder = saveSalesOrderInTestDB();
        List<SalesOrder> salesOrdersList = orderRepository.findByCustomerId(savedSalesOrder.getCustomer_sos().getCust_id());
        assertFalse(CollectionUtils.isEmpty(salesOrdersList));
        orderRepository.delete(savedSalesOrder);
    }

//    @Test
//    public void testDeleteOrderByOrderId(){
//        SalesOrder savedSalesOrder = saveSalesOrderInTestDB();
//        int allOrders = orderRepository.findAll().size();
//        orderRepository.deleteById(orderRepository.findAll().get(0).getId());
//        assertEquals(allOrders-1,orderRepository.findAll().size());
//        orderRepository.delete(savedSalesOrder);
//    }
}
