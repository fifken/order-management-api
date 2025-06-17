package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrdersResponseDto {
    private Long id;
    private Long customerId;
    private Double amount;
    private LocalDate orderDate;
    public void setAmount(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'setAmount'");
    }
    public void setAmount(Double amount2) {
        throw new UnsupportedOperationException("Unimplemented method 'setAmount'");
    }
}
