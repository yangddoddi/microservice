package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.RequestOrder;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    default OrderDto of(RequestOrder dto, String userId) {
        return OrderDto.builder()
                .productId(dto.getProductId())
                .qty(dto.getQty())
                .unitPrice(dto.getUnitPrice())
                .totalPrice(dto.getUnitPrice() * dto.getQty())
                .userId(userId)
                .orderId(UUID.randomUUID().toString())
                .build();
    }
}
