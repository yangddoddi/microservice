package com.example.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private String encryptedPassword;
}
