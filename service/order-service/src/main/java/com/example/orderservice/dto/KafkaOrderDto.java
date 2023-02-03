package com.example.orderservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KafkaOrderDto implements Serializable {
    private Schema schema;
    private Payload payload;
}
