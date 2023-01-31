package com.example.userservice.mapper;

import com.example.userservice.dto.ResponseUser;
import com.example.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    ResponseUser of(User user);
}
