package com.example.orderservice.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;
}
