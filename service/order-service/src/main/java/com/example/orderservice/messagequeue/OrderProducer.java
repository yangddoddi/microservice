package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("int32", true, "qty"),
            new Field("int32", true, "unit_price"),
            new Field("int32", true, "total_price"));

    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("orders")
            .build();


    public OrderDto send(String topic, OrderDto orderDto) {
        String json = "";

        Payload payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .qty(orderDto.getQty())
                .product_id(orderDto.getProductId())
                .total_price(orderDto.getTotalPrice())
                .unit_price(orderDto.getUnitPrice())
                .build();

        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.builder()
                .payload(payload)
                .schema(schema)
                .build();

        try {
            json = objectMapper.writeValueAsString(kafkaOrderDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, json);
        log.info("Kafka Producer sent data from the order microservice : " + orderDto);

        return orderDto;
    }
}
