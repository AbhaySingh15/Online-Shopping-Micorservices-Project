package com.abhay.salesorderservice;

import com.abhay.salesorderservice.controller.SalesOrderController;
import com.abhay.salesorderservice.entity.Item;
import com.abhay.salesorderservice.model.SalesOrderRequestModel;
import com.abhay.salesorderservice.model.SalesOrderResponseModel;
import com.abhay.salesorderservice.repository.OrderRepository;
import com.abhay.salesorderservice.service.OrderService;
import com.netflix.discovery.converters.Auto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
class SalesSalesOrderServiceApplicationTests {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SalesOrderController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

    @Test
    void getItemsFromItemService(){
        StringBuilder getItemByNamesFromItemService = new StringBuilder("http://item-service/item?name=");
        String itemNamesRequestedByCustomer = String.join(",","iphone-13","t-shirt","dell mouse");
        getItemByNamesFromItemService.append(itemNamesRequestedByCustomer);

        ResponseEntity<Item[]> itemArray = restTemplate.getForEntity(getItemByNamesFromItemService.toString(), Item[].class);
        assertNotNull(itemArray);
    }

}
