package com.example.userservice.service;

import com.example.userservice.dto.ResponseUser;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;

import java.util.List;

public interface UserService {
    ResponseUser saveUser(UserDto userDto);
    ResponseUser getUserByUserId(String userId);
    List<ResponseUser> getUserByAll();
}
