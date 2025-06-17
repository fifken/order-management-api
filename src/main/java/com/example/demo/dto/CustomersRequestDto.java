package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import  com.example.demo.validation.UniqueEmail;

@Data
public class CustomersRequestDto {
    @NotBlank
    private String name;

    @UniqueEmail
    @Email
    @NotBlank
    private String email;

}

