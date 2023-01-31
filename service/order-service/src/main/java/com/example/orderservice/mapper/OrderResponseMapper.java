package com.example.orderservice.mapper;

import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    ResponseOrder of(Order order);
    List<ResponseOrder> of(List<Order> orders);
}
