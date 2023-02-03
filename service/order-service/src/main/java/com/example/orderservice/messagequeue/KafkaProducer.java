package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.ResponseOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ResponseOrder send(String topic, ResponseOrder orderDto) {
        String json = "";

        try {
            json = objectMapper.writeValueAsString(orderDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, json);
        log.info("Kafka Producer sent data from the order microservice : " + orderDto);

        return orderDto;
    }
}
