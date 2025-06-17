package com.example.demo.repository;

import com.example.demo.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByEmail(String email);
}
