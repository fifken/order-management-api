package com.example.demo.controller;

import com.example.demo.dto.OrdersRequestDto;
import com.example.demo.dto.OrdersResponseDto;
import com.example.demo.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService2) {
    }

    @PostMapping
    public OrdersResponseDto createOrder(@Valid @RequestBody OrdersRequestDto dto) {
        return ordersService.createOrder(dto);
    }

    @GetMapping("/{id}")
    public OrdersResponseDto getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }
}
