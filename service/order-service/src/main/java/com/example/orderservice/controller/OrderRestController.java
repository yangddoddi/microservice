package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.mapper.OrderRequestMapper;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;
    private final Environment environment;
    private final OrderRequestMapper orderRequestMapper;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in user service %s",
                environment.getProperty("local.server.port"));
    }

    @PostMapping("/{user-id}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@Valid @RequestBody RequestOrder request,
                                                    @PathVariable(name = "user-id") String userId) {
        // jpa
        OrderDto dto = orderRequestMapper.of(request, userId);
        ResponseOrder response = orderService.createOrder(dto);

        // kafka
        kafkaProducer.send("catalog-topic", response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{user-id}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable(name = "user-id") String userId) {
        List<ResponseOrder> response = orderService.getOrderByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/orders/{order-id}")
    public ResponseEntity<ResponseOrder> getOrder(@PathVariable(name = "order-id") String orderId) {
        ResponseOrder response = orderService.getOrderByOrderId(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
