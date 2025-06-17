package com.example.demo.service;

import com.example.demo.dto.OrdersRequestDto;
import com.example.demo.dto.OrdersResponseDto;
import com.example.demo.model.Orders;
import com.example.demo.model.Customers;
import com.example.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersService customersService;

    public OrdersResponseDto createOrder(OrdersRequestDto dto) {
        Customers customer = customersService.getCustomerById(dto.getCustomerId());

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setAmount(dto.getAmount());
        order.setOrderDate(LocalDate.now());

        Orders saved = ordersRepository.save(order);

        OrdersResponseDto response = new OrdersResponseDto();
        response.setId(saved.getId());
        response.setCustomerId(saved.getCustomer().getId());
        response.setAmount(saved.getAmount());
        response.setOrderDate(saved.getOrderDate());

        return response;
    }

    public List<OrdersResponseDto> getOrdersByCustomerId(Long customerId) {
        Customers customer = customersService.getCustomerById(customerId);
        return ordersRepository.findByCustomer(customer).stream().map(order -> {
            OrdersResponseDto dto = new OrdersResponseDto();
            dto.setId(order.getId());
            dto.setCustomerId(customer.getId());
            dto.setAmount(order.getAmount());
            dto.setOrderDate(order.getOrderDate());
            return dto;
        }).collect(Collectors.toList());
    }

    public OrdersResponseDto getOrderById(Long id) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrdersResponseDto dto = new OrdersResponseDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setAmount(order.getAmount());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
