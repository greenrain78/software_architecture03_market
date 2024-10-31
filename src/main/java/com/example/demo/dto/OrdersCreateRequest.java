package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrdersCreateRequest {
    @Schema(description = "상품 이름", example = "사과")
    private String productName;
    @Schema(description = "고객 이름", example = "홍길동")
    private String customerName;
    @Schema(description = "수량", example = "3")
    private int quantity;
}
