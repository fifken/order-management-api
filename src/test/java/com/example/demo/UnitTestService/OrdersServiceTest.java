package com.example.demo.UnitTestService;

import com.example.demo.dto.OrdersRequestDto;
import com.example.demo.model.Customers;
import com.example.demo.model.Orders;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private OrdersService ordersService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder_Success() {
        OrdersRequestDto dto = new OrdersRequestDto();
        dto.setCustomerId(1L);
        dto.setAmount(100000);
        dto.setOrderDate(LocalDate.now());

        Customers customer = new Customers();
        customer.setId(1L);
        customer.setName("Alice");

        when(customersRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(ordersRepository.save(any(Orders.class))).thenAnswer(i -> i.getArgument(0));

        var result = ordersService.createOrder(dto);

        assertNotNull(result);
        assertEquals(100000, result.getAmount());
    }
}