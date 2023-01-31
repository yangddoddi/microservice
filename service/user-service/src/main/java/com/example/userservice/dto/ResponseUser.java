package com.example.userservice.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUser {
    private String email;
    private String name;
    private String userId;
}
