package com.example.userservice.dto;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class RequestUser {
    @NotNull
    @Email
    @Size(min = 2)
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @Size(min = 2)
    private String name;
}
