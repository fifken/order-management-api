package com.example.demo.UnitTestController;

import com.example.demo.dto.CustomersRequestDto;
import com.example.demo.dto.CustomersResponseDto;
import com.example.demo.controller.CustomersController;
import com.example.demo.service.CustomersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomersControllerTest {

    private final CustomersService customersService = Mockito.mock(CustomersService.class);
    private final CustomersController controller = new CustomersController(customersService);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    void testCreateCustomer() throws Exception {
        CustomersRequestDto request = new CustomersRequestDto();
        request.setName("Alice");
        request.setEmail("alice@example.com");

        CustomersResponseDto response = CustomersResponseDto.builder()
                .id(1L)
                .name("Alice")
                .email("alice@example.com")
                .build();

        Mockito.when(customersService.createCustomer(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }
}