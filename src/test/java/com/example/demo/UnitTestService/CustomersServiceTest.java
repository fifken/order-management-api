package com.example.demo.UnitTestService;

import com.example.demo.dto.CustomersRequestDto;
import com.example.demo.model.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersService customersService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer_Success() {
        CustomersRequestDto dto = new CustomersRequestDto();
        dto.setName("Alice");
        dto.setEmail("alice@example.com");

        when(customersRepository.findByEmail("alice@example.com")).thenReturn(Optional.empty());

        Customers savedCustomer = new Customers();
        savedCustomer.setId(1L);
        savedCustomer.setName("Alice");
        savedCustomer.setEmail("alice@example.com");

        when(customersRepository.save(any(Customers.class))).thenReturn(savedCustomer);

        var response = customersService.createCustomer(dto);

        assertEquals("Alice", response.getName());
        assertEquals("alice@example.com", response.getEmail());
        verify(customersRepository).save(any(Customers.class));
    }

    @Test
    void testCreateCustomer_EmailAlreadyRegistered() {
        CustomersRequestDto dto = new CustomersRequestDto();
        dto.setName("Bob");
        dto.setEmail("bob@example.com");

        when(customersRepository.findByEmail("bob@example.com"))
            .thenReturn(Optional.of(new Customers()));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            customersService.createCustomer(dto);
        });

        assertEquals("Email already registered", exception.getMessage());
        verify(customersRepository, never()).save(any());
    }
}