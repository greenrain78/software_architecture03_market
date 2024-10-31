package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrdersResponse {
    private Long id;
    private String productName;
    private String customerName;
    private int quantity;
    private int price;
    private LocalDateTime createdAt;
}
