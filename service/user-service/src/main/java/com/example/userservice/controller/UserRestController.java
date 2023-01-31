package com.example.userservice.controller;

import com.example.userservice.Greeting;
import com.example.userservice.dto.RequestUser;
import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserDtoMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserRestController {
    private final Greeting greeting;
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in user service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser request) {
        UserDto dto = userDtoMapper.of(request);
        ResponseUser response = userDtoMapper.of(userService.saveUser(dto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
