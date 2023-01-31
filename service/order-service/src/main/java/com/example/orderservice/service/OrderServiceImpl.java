package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderDtoMapper;
import com.example.orderservice.mapper.OrderResponseMapper;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderDtoMapper orderDtoMapper;

    @Override
    public ResponseOrder createOrder(OrderDto orderDto) {
        Order order = orderDtoMapper.of(orderDto);
        orderRepository.save(order);

        return orderResponseMapper.of(order);
    }

    @Override
    public ResponseOrder getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        return orderResponseMapper.of(order);
    }

    @Override
    public List<ResponseOrder> getOrderByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orderResponseMapper.of(orders);
    }
}
