package com.abhay.salesorderservice.service.impl;

import com.abhay.salesorderservice.entity.*;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.repository.Customer_SOS_Repository;
import com.abhay.salesorderservice.repository.OrderRepository;
import com.abhay.salesorderservice.repository.Order_line_Item_Repository;
import com.abhay.salesorderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Order_line_Item_Repository order_line_item_repository;

    @Autowired
    private Customer_SOS_Repository customer_sos_repository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ResponseEntity<SalesOrderResponseModel> createOrder(SalesOrderRequestModel orderRequestModel) {
        // getting cust_id from orderRequestModel object to check if customer is registered or not
        Optional<CustomerSOS> customerSOS = customer_sos_repository.findById(orderRequestModel.getCust_id());
        if(customerSOS.isPresent()){ // if customer is registered
            // mapping salesOrder object from salesOrderRequestModel object
            SalesOrder salesOrder = modelMapper.map(orderRequestModel, SalesOrder.class);
            // setting customer id for the order object
            salesOrder.setCustomer_sos(customerSOS.get());
            log.info(salesOrder.toString());
            // url to get the requested items from the item service
            StringBuilder getItemByNamesFromItemService = new StringBuilder("http://item-service/item?name=");
            String listOfItemNames = String.join(",",orderRequestModel.getItem_names());
            log.info(listOfItemNames);
            getItemByNamesFromItemService.append(listOfItemNames);
            log.info(getItemByNamesFromItemService.toString());
            // getting the item array containing all items from item service
            ResponseEntity<Item[]> responseEntity = restTemplate.getForEntity(getItemByNamesFromItemService.toString(),Item[].class);
            Item[] itemArray = responseEntity.getBody();
            log.info(Arrays.toString(itemArray));
            if(itemArray.length==0){
                log.info("Order cannot be placed as there is no item available in the stock");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            orderRepository.save(salesOrder);
            List<Order_Line_Item> order_line_items_list = new ArrayList<>();
            for(Item item: itemArray){
                // creating order_line_item object from item object and setting salesOrder
                // object to it
                Order_Line_Item order_line_item = createOrderLineItem(item,salesOrder);
                order_line_items_list.add(order_line_item);
            }
            order_line_item_repository.saveAll(order_line_items_list);
            SalesOrderResponseModel salesOrderResponseModel = modelMapper.map(salesOrder, SalesOrderResponseModel.class);
            salesOrderResponseModel.setOrder_line_items(order_line_items_list);
            return ResponseEntity.ok(salesOrderResponseModel);
        }else{
            log.info("order cannot be placed as customer is not registered");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<SalesOrder> getOrderDetailsByCustomerId(Long cust_id) {
        SalesOrder salesOrder = orderRepository.findByCustomerId(cust_id);
        return ResponseEntity.ok(salesOrder);
    }

    @Override
    public ResponseEntity<SalesOrderResponseModel> getOrderDetailsByOrderId(Long order_id) {
        Optional<SalesOrder> salesOrderEntity = orderRepository.findById(order_id);
        if(salesOrderEntity.isPresent()){
            SalesOrder salesOrder = salesOrderEntity.get();
            SalesOrderResponseModel salesOrderResponseModel = modelMapper.map(salesOrder,SalesOrderResponseModel.class);
            return ResponseEntity.ok(salesOrderResponseModel);
        }
        return ResponseEntity.badRequest().build();
    }

    public Order_Line_Item createOrderLineItem(Item item,SalesOrder salesOrder){
        Order_Line_Item order_line_item = new Order_Line_Item();
        order_line_item.setName(item.getName());
        order_line_item.setSalesOrder(salesOrder);
        log.info(order_line_item.getName());
        order_line_item_repository.save(order_line_item);
        return order_line_item;
    }

    @PostConstruct
    public void setPropertyMapperForSalesOrder(){
        TypeMap<SalesOrderRequestModel, SalesOrder> salesOrderTypeMap = modelMapper.createTypeMap(SalesOrderRequestModel.class, SalesOrder.class);
        salesOrderTypeMap.addMapping(SalesOrderRequestModel::getOrder_desc,SalesOrder::setOrder_desc);
        salesOrderTypeMap.addMapping(SalesOrderRequestModel::getOrder_date,SalesOrder::setOrder_date);

        TypeMap<SalesOrder, SalesOrderResponseModel> salesOrderResponseModelTypeMap = modelMapper.createTypeMap(SalesOrder.class, SalesOrderResponseModel.class);
        salesOrderResponseModelTypeMap.addMapping(SalesOrder::getOrder_desc,SalesOrderResponseModel::setOrder_desc);
        salesOrderResponseModelTypeMap.addMapping(SalesOrder::getId,SalesOrderResponseModel::setId);
    }


}
