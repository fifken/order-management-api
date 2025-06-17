package com.example.demo.UnitTestController;

import com.example.demo.dto.OrdersRequestDto;
import com.example.demo.dto.OrdersResponseDto;
import com.example.demo.service.OrdersService;
import com.example.demo.controller.OrdersController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrdersControllerTest {

    private final OrdersService ordersService = Mockito.mock(OrdersService.class);
    private final OrdersController controller = new OrdersController(ordersService);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    void testCreateOrder() throws Exception {
        OrdersRequestDto request = new OrdersRequestDto();
        request.setCustomerId(1L);
        request.setAmount(150000);
        request.setOrderDate(LocalDate.of(2025, 6, 17));

        OrdersResponseDto response = new OrdersResponseDto();
        response.setId(1L);
        response.setCustomerId(1L);
        response.setAmount(150000);
        response.setOrderDate(LocalDate.of(2025, 6, 17));

        Mockito.when(ordersService.createOrder(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(150000))
                .andExpect(jsonPath("$.customerId").value(1));
    }
}