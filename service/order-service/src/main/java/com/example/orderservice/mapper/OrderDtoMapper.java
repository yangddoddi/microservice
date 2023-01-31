package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {
    default Order of (OrderDto dto) {
        return Order.builder()
                .orderId(dto.getOrderId())
                .userId(dto.getUserId())
                .qty(dto.getQty())
                .productId(dto.getProductId())
                .totalPrice(dto.getTotalPrice())
                .unitPrice(dto.getUnitPrice())
                .build();
    }
}
