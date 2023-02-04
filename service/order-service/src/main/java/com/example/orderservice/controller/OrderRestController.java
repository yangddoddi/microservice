package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.dto.ResponseOrder;
import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderDtoMapper;
import com.example.orderservice.mapper.OrderRequestMapper;
import com.example.orderservice.mapper.OrderResponseMapper;
import com.example.orderservice.messagequeue.CatalogProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;
    private final Environment environment;
    private final OrderRequestMapper orderRequestMapper;
    private final CatalogProducer catalogProducer;
    private final OrderDtoMapper orderDtoMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in user service %s",
                environment.getProperty("local.server.port"));
    }

    @PostMapping("/{user-id}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@Valid @RequestBody RequestOrder request,
                                                    @PathVariable(name = "user-id") String userId) {
        // jpa
//        OrderDto dto = orderRequestMapper.of(request, userId);
//        ResponseOrder response = orderService.createOrder(dto);

        OrderDto orderDto = orderRequestMapper.of(request, userId);
        Order order = orderDtoMapper.of(orderDto);

        // order kafka
        orderProducer.send("orders", orderDto);

        // catalog kafka
        ResponseOrder response = orderResponseMapper.of(order);
        catalogProducer.send("catalog-topic", response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{user-id}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable(name = "user-id") String userId) {
        log.info("Before retrieve orders data");
        List<ResponseOrder> response = orderService.getOrderByUserId(userId);
        log.info("After retrieve orders data");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/orders/{order-id}")
    public ResponseEntity<ResponseOrder> getOrder(@PathVariable(name = "order-id") String orderId) {
        ResponseOrder response = orderService.getOrderByOrderId(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
