package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersRequestDto {

    @NotNull
    private Long customerId;

    @DecimalMin("0.01")
    private Double amount;

    public void setAmount(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'setAmount'");
    }

    public void setOrderDate(LocalDate of) {
        throw new UnsupportedOperationException("Unimplemented method 'setOrderDate'");
    }
}
