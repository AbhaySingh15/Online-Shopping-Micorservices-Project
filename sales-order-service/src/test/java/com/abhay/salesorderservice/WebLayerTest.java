package com.abhay.salesorderservice;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
    public void shouldCreateOrder() throws Exception {

        ResponseEntity responseEntity = ResponseEntity.ok("Order created");
        //Mocking OrderService createOrder() method
        when(orderService.createOrder(any())).thenReturn(responseEntity);

        // creating test salesOrderDto object
        SalesOrderDto salesOrderDto = new SalesOrderDto();
        salesOrderDto.setCust_id(1L);
        salesOrderDto.setOrder_desc("testing sales order dto");
        List<String> itemList = List.of("Laptop","Monitor");
        salesOrderDto.setItem_names(itemList);

        //Converting salesOrderDto to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String salesOrderDtoJson = gson.toJson(salesOrderDto);

        //Performing mock request
        mockMvc.perform(post("/orders").content(salesOrderDtoJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Order created"));
    }
}
