package com.example.firstservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
public class FirstServiceController {
    @GetMapping("/welcome")
    public String welcome(@RequestHeader(name = "first-request") String header) {
        System.out.println(header);
        return "welcome to the first service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader(name = "first-request") String header) {
        return header;
    }

    @GetMapping("/check")
    public String message() {
        return "hi, there. this is a message from first service";
    }
}
