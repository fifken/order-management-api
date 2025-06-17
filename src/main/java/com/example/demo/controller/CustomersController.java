package com.example.demo.controller;

import com.example.demo.dto.CustomersRequestDto;
import com.example.demo.dto.CustomersResponseDto;
import com.example.demo.dto.OrdersResponseDto;
import com.example.demo.service.CustomersService;
import com.example.demo.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private OrdersService ordersService;

    public CustomersController(CustomersService customersService2) {
    
    }

    @PostMapping
    public CustomersResponseDto createCustomer(@Valid @RequestBody CustomersRequestDto dto) {
        return customersService.createCustomer(dto);
    }

    @GetMapping("/{id}/orders")
    public List<OrdersResponseDto> getOrdersByCustomer(@PathVariable Long id) {
        return ordersService.getOrdersByCustomerId(id);
    }
}
