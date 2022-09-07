package com.abhay.salesorderservice.service;

import com.abhay.salesorderservice.entity.*;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.repository.Customer_SOS_Repository;
import com.abhay.salesorderservice.repository.OrderRepository;
import com.abhay.salesorderservice.repository.Order_line_Item_Repository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService{

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
    public SalesOrderResponseModel createOrder(SalesOrderRequestModel orderRequestModel) {
        TypeMap<SalesOrderRequestModel, SalesOrder> propertyMapper = modelMapper.createTypeMap(SalesOrderRequestModel.class, SalesOrder.class);
        propertyMapper.addMapping(SalesOrderRequestModel::getOrder_desc,SalesOrder::setOrder_desc);
        propertyMapper.addMapping(SalesOrderRequestModel::getOrder_date,SalesOrder::setOrder_date);
        propertyMapper.addMapping(SalesOrderRequestModel::getCust_id,SalesOrder::setCustomer_sos);

        SalesOrder salesOrder = this.modelMapper.map(orderRequestModel, SalesOrder.class);
        log.info(salesOrder.toString());
        Optional<CustomerSOS> customerSOS = customer_sos_repository.findById(orderRequestModel.getCust_id());
        if(customerSOS.isPresent()){
            salesOrder.setCustomer_sos(customerSOS.get());
//            ItemList itemList = restTemplate.getForObject("http://localhost:9001/item?name=iphone-13,asus_zephyrus_g14", ItemList.class);
//            List<Item> listOfItems = itemList.getItemList();
            ResponseEntity<Item[]> responseEntity = restTemplate.getForEntity("http://localhost:9001/item?name=iphone-13,asus_zephyrus_g14",Item[].class);
            Item[] itemArray = responseEntity.getBody();
            log.info(Arrays.toString(itemArray));
            for(Item item: itemArray){
                Order_Line_Item order_line_item = new Order_Line_Item();
                order_line_item.setName(item.getName());
                orderRepository.save(salesOrder);
                order_line_item.setSalesOrder(salesOrder);
                log.info(order_line_item.getName());
                order_line_item_repository.save(order_line_item);
            }

            return null;
        }else{
            log.info("customer is not registered");
        }
        return null;
    }

    @Override
    public ResponseEntity getOrderDetails(Long cust_id) {
        return null;
    }
}
