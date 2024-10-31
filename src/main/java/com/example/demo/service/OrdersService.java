package com.example.demo.service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Product;
import com.example.demo.dto.OrdersCreateRequest;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    public List<Orders> getOrdersByCustomerName(String customerName) {
        return ordersRepository.findByCustomerName(customerName);
    }


    public Orders createOrder(OrdersCreateRequest ordersCreateRequest) {
        Product product = productRepository.findByName(ordersCreateRequest.getProductName()).stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다: " + ordersCreateRequest.getProductName()));
        // 주문 가능 수량 검증
        if (product.getQuantity() < ordersCreateRequest.getQuantity()) {
            throw new IllegalArgumentException("주문 가능 수량을 초과했습니다: " + ordersCreateRequest.getQuantity());
        }
        product.setQuantity(product.getQuantity() - ordersCreateRequest.getQuantity());
        productRepository.save(product);

        // 주문 가격 계산
        int totalPrice = product.getPrice() * ordersCreateRequest.getQuantity();
        Orders orders = Orders.builder()
                .productName(ordersCreateRequest.getProductName())
                .customerName(ordersCreateRequest.getCustomerName())
                .status("주문완료")
                .quantity(ordersCreateRequest.getQuantity())
                .price(totalPrice)
                .build();
        return ordersRepository.save(orders);
    }
    // 주문 가능한 상품 조회 요청
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
