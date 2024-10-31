package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrdersResponse {
    @Schema(description = "주문 ID", example = "1")
    private Long id;
    @Schema(description = "상품 이름", example = "사과")
    private String productName;
    @Schema(description = "고객 이름", example = "홍길동")
    private String customerName;
    @Schema(description = "주문 상태", example = "주문 완료")
    private String status;
    @Schema(description = "수량", example = "3")
    private int quantity;
    @Schema(description = "가격", example = "3000")
    private int price;
    @Schema(description = "생성일시", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;
}
