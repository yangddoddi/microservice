package com.example.orderservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payload implements Serializable {
    private String order_id;
    private String user_id;
    private String product_id;
    private Integer qty;
    private Integer unit_price;
    private Integer total_price;
}
