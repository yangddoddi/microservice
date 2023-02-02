package com.example.userservice.mapper;

import com.example.userservice.dto.ResponseOrder;
import com.example.userservice.dto.ResponseUser;
import com.example.userservice.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    ResponseUser of(User user);
    ResponseUser of(User user, List<ResponseOrder> orders);
}
