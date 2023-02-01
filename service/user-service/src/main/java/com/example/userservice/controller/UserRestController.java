package com.example.userservice.controller;

import com.example.userservice.Greeting;
import com.example.userservice.dto.RequestUser;
import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserDtoMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserRestController {
    private final Greeting greeting;
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;
    private final Environment environment;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in user service %s",
                environment.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser request) {
        UserDto dto = userDtoMapper.of(request);
        ResponseUser response = userService.saveUser(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        List<ResponseUser> response = userService.getUserByAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable(name = "user-id") String userId) {
        ResponseUser response = userService.getUserByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<> loginUser(@RequestBody(RequestLogin request)) {

    }
}
