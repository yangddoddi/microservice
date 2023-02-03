package com.example.orderservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Field implements Serializable {
    private String type;
    private Boolean optional;
    private String field;
}
