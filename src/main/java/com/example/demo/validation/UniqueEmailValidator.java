package com.example.demo.validation;

import com.example.demo.repository.CustomersRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !customersRepository.findByEmail(email).isPresent();
    }
}
