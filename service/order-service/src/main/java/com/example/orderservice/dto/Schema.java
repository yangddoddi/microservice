package com.example.orderservice.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schema implements Serializable {
    private String type;
    private List<Field> fields;
    private Boolean optional;
    private String name;
}
