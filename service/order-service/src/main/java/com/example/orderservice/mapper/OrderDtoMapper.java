package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {
    Order of (OrderDto dto);
}
