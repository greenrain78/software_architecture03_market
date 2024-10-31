package com.example.demo.controller;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Product;
import com.example.demo.dto.OrdersCreateRequest;
import com.example.demo.dto.OrdersResponse;
import com.example.demo.dto.ProductResponse;
import com.example.demo.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrdersService ordersService;

    @PostMapping("/")
    @Operation(summary = "주문 생성", description = "주문을 생성합니다.")
    public ResponseEntity<OrdersResponse> createOrder(@RequestBody OrdersCreateRequest orders) {
        log.info("주문 생성 요청");
        Orders order = ordersService.createOrder(orders);
        return ResponseEntity.ok(OrdersResponse.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .customerName(order.getCustomerName())
                .status(order.getStatus())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .createdAt(order.getCreatedAt())
                .build());
    }

    @GetMapping("/user")
    @Operation(summary = "사용자 주문 조회", description = "사용자의 주문을 조회합니다.")
    public ResponseEntity<List<OrdersResponse>> getOrders(@RequestParam String customerName) {
        log.info("사용자 주문 조회 요청");
        List<Orders> orders = ordersService.getOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orders.stream().map(order -> OrdersResponse.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .customerName(order.getCustomerName())
                .status(order.getStatus())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .createdAt(order.getCreatedAt())
                .build()).toList());
    }
    @GetMapping("/product")
    @Operation(summary = "주문 가능한 상품 조회", description = "주문 가능한 상품을 조회합니다.")
    public ResponseEntity<List<ProductResponse>> getOrdersByProductName() {
        log.info("주문 가능한 상품 조회 요청");
        List<Product> orders = ordersService.getProducts();
        return ResponseEntity.ok(orders.stream().map(order -> ProductResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build()).toList());
    }
}
