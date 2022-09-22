package com.abhay.salesorderservice.service.impl;

import com.abhay.salesorderservice.entity.CustomerSOS;
import com.abhay.salesorderservice.entity.Item;
import com.abhay.salesorderservice.entity.Order_Line_Item;
import com.abhay.salesorderservice.entity.SalesOrder;
import com.abhay.salesorderservice.model.SalesOrderDto;
import com.abhay.salesorderservice.repository.Customer_SOS_Repository;
import com.abhay.salesorderservice.repository.OrderRepository;
import com.abhay.salesorderservice.repository.Order_line_Item_Repository;
import com.abhay.salesorderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
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
    public ResponseEntity<?> createOrder(SalesOrderDto salesOrderDtoInput) {
        // getting cust_id from orderRequestModel object to check if customer is registered or not
        Optional<CustomerSOS> customer = customer_sos_repository.findById(salesOrderDtoInput.getCust_id());
        // if customer is registered & item array is not null or empty
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order cannot be placed as customer is not registered");
        } else {
            Item[] itemArray = getItemsFromItemService(salesOrderDtoInput.getItem_names());
            if (ArrayUtils.isEmpty(itemArray)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order cannot be placed as not a single item requested by customer is in the stock");
            } else {
                // mapping salesOrder object from salesOrderRequestModel object
                SalesOrder salesOrder = modelMapper.map(salesOrderDtoInput, SalesOrder.class);
                // setting customer id for the order object
                salesOrder.setCustomer_sos(customer.get());
                log.info(salesOrder.toString());
                List<Order_Line_Item> order_line_itemList = setOrderLineItemList(itemArray, salesOrder);
                salesOrder.setOrder_line_itemList(order_line_itemList);
                orderRepository.save(salesOrder);
                order_line_item_repository.saveAll(order_line_itemList);
                SalesOrderDto salesOrderDto = modelMapper.map(salesOrder, SalesOrderDto.class);
                return ResponseEntity.ok(salesOrderDto);
            }
        }
    }

    @Override
    public ResponseEntity<?> getOrderDetailsByCustomerId(Long cust_id) {
        List<SalesOrder> salesOrder = orderRepository.findByCustomerId(cust_id);
        List<SalesOrderDto> salesOrderDto = salesOrder.stream()
                .map(order -> modelMapper.map(order, SalesOrderDto.class))
                .collect(Collectors.toList());
        log.info(salesOrderDto.toString());
        return ResponseEntity.ok(salesOrderDto);
    }

    @Override
    public ResponseEntity<?> getOrderDetailsByOrderId(Long order_id) {
        Optional<SalesOrder> salesOrderEntity = orderRepository.findById(order_id);
        if (salesOrderEntity.isPresent()) {
            SalesOrder salesOrder = salesOrderEntity.get();
            SalesOrderDto salesOrderDto = modelMapper.map(salesOrder, SalesOrderDto.class);
            return ResponseEntity.ok(salesOrderDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no order found for given order id");
    }

    public Item[] getItemsFromItemService(List<String> itemNameList) {
        // url to get the requested items from the item service
        StringBuilder getItemByNamesFromItemService = new StringBuilder("http://item-service/item?name=");
        String itemNamesRequestedByCustomer = String.join(",", itemNameList);
        log.info(itemNamesRequestedByCustomer);
        getItemByNamesFromItemService.append(itemNamesRequestedByCustomer);
        log.info(getItemByNamesFromItemService.toString());
        // getting the item array containing all items from item service
        ResponseEntity<Item[]> responseEntity = restTemplate.getForEntity(getItemByNamesFromItemService.toString(), Item[].class);
        Item[] itemArray = responseEntity.getBody();
        log.info(Arrays.toString(itemArray));
        if (ArrayUtils.isEmpty(itemArray)) {
            log.info("order cannot be placed as not a single item requested by customer is in the stock");
            return null;
        } else {
            return itemArray;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateOrder(Long orderId, SalesOrderDto salesOrderDtoInput) {
        try {
            SalesOrder salesOrder = orderRepository.getReferenceById(orderId);
            Item[] itemArray = getItemsFromItemService(salesOrderDtoInput.getItem_names());
            if (ArrayUtils.isNotEmpty(itemArray)) {
                order_line_item_repository.deleteBySalesOrderId(orderId);
                List<Order_Line_Item> updatedOrderLineItemList = setOrderLineItemList(itemArray, salesOrder);
                salesOrder.setOrderLineItemList(updatedOrderLineItemList);
                orderRepository.save(salesOrder);
                order_line_item_repository.saveAll(updatedOrderLineItemList);
                SalesOrderDto salesOrderDto = modelMapper.map(salesOrder, SalesOrderDto.class);
                return ResponseEntity.ok(salesOrderDto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order cannot be updated as items requested are not available");
            }
        } catch (EntityNotFoundException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order cannot be updated as order id is invalid");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteOrderByOrderId(Long orderId) {
        Optional<SalesOrder> optionalSalesOrder = orderRepository.findById(orderId);
        if (optionalSalesOrder.isPresent()) {
            // order line items will get automatically deleted
            // because of cascade.remove operation
            orderRepository.deleteById(orderId);
            return ResponseEntity.status(HttpStatus.OK).body("order successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order doesn't exist so it can't be deleted");
        }
    }

    public List<Order_Line_Item> setOrderLineItemList(Item[] items, SalesOrder salesOrder) {
        List<Order_Line_Item> order_line_itemList = new ArrayList<>();
        for (Item item : items) {
            Order_Line_Item order_line_item = new Order_Line_Item();
            order_line_item.setName(item.getName());
            order_line_item.setSalesOrder(salesOrder);
            log.info(order_line_item.getName());
            order_line_itemList.add(order_line_item);
        }
        return order_line_itemList;
    }
}
