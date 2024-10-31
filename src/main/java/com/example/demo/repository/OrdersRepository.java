package com.example.demo.repository;

import com.example.demo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerName(String customerName);
}
