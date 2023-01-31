package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer stock;
    private Integer totalPrice;
    private Date createdAt;
    private String orderId;
}
