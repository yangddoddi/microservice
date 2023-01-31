package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    ResponseOrder createOrder(OrderDto order);
    ResponseOrder getOrderByOrderId(String orderId);
    List<ResponseOrder> getOrderByUserId(String userId);
}
