package com.example.demo.repository;

import com.example.demo.model.Orders;
import com.example.demo.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomer(Customers customer);
}
