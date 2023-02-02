package com.example.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RequestLogin {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
