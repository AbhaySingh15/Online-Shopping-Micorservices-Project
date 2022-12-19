package com.abhay.salesorderservice;

import com.abhay.salesorderservice.controller.SalesOrderController;
import com.abhay.salesorderservice.dto.SalesOrderDto;
import com.abhay.salesorderservice.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testCreateOrderController() throws Exception {

        ResponseEntity responseEntity = ResponseEntity.ok("Order created");
        //Mocking OrderService createOrder() method
        when(orderService.createOrder(any(SalesOrderDto.class))).thenReturn(responseEntity);

        //creating test salesOrderDto object
        SalesOrderDto salesOrderDto = new SalesOrderDto();
        salesOrderDto.setCust_id(1L);
        salesOrderDto.setOrder_desc("testing sales order dto");
        List<String> itemList = List.of("Laptop","Monitor");
        salesOrderDto.setItem_names(itemList);

        //Converting salesOrderDto to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String salesOrderDtoJson = gson.toJson(salesOrderDto);

        //Performing mock request and testing valid input
        //Should return ok status
        mockMvc.perform(post("/orders").content(salesOrderDtoJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Order created"));

        salesOrderDto.setItem_names(null);
        salesOrderDtoJson = gson.toJson(salesOrderDto);

        //Performing mock request and testing invalid input
        //Should return bad request
        mockMvc.perform(post("/orders").content(salesOrderDtoJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetCustomerDetailsByOrderId() throws Exception {
        SalesOrderDto salesOrderDto = new SalesOrderDto();
        salesOrderDto.setOrder_desc("test order");
        ResponseEntity responseEntity = ResponseEntity.ok(salesOrderDto);
        when(orderService.getOrderDetailsByOrderId(any())).thenReturn(responseEntity);

        mockMvc.perform(get("/orders/{orderId}",2L)).andDo(print())
                .andExpect(content().json("{\"order_desc\":\"test order\"}"))
                .andReturn();
    }


}
