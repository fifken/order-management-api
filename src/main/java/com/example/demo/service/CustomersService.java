package com.example.demo.service;

import com.example.demo.dto.CustomersRequestDto;
import com.example.demo.dto.CustomersResponseDto;
import com.example.demo.model.Customers;
import com.example.demo.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    public CustomersResponseDto createCustomer(CustomersRequestDto dto) {
        if (customersRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Customers customer = new Customers();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());

        Customers saved = customersRepository.save(customer);

        // CustomersResponseDto response = new CustomersResponseDto();
        CustomersResponseDto response = CustomersResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .build();

        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        return response;
    }

    public Customers getCustomerById(Long id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
