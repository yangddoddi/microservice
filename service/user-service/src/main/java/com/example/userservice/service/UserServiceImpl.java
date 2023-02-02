package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.ResponseOrder;
import com.example.userservice.dto.ResponseUser;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserDtoMapper;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.mapper.UserResponseMapper;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final UserDtoMapper userDtoMapper;
    private final RestTemplate restTemplate;
    private final Environment environment;
    private final OrderServiceClient orderServiceClient;

    @Override
    public ResponseUser saveUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userMapper.of(userDto);
        userRepository.save(user);

        return userResponseMapper.of(user);
    }

    @Override
    public ResponseUser getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new);

        // using as rest template
//        String url = String.format(Objects.requireNonNull(environment.getProperty("order_service.url")), userId);
//        ResponseEntity<List<ResponseOrder>> orderResponse =
//                restTemplate.exchange(url, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<ResponseOrder>>() {
//        });

        // using as feign client
        List<ResponseOrder> orders = orderServiceClient.getOrders(userId);

        return userResponseMapper.of(user, orders);
    }

    @Override
    public List<ResponseUser> getUserByAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userResponseMapper::of)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);

        return userDtoMapper.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository
                .findByEmail(username)
                .orElseThrow(NoSuchElementException::new);

        return new org.springframework.security.core.userdetails.User
                (findUser.getEmail(), findUser.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }
}
