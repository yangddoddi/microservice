package com.example.userservice.service;

import com.example.userservice.dto.ResponseUser;
import com.example.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    ResponseUser saveUser(UserDto userDto);
    ResponseUser getUserByUserId(String userId);
    List<ResponseUser> getUserByAll();
}
