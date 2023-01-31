package com.example.userservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Greeting {
    private final String message;

    public Greeting(@Value("${greeting.message}") String message) {
        this.message = message;
    }
}
