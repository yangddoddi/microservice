package com.example.secondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome(@RequestHeader(name = "second-request") String header) {
        System.out.println(header);
        return "welcome to the second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader(name = "second-request") String header) {
        return header;
    }

    @GetMapping("/check")
    public String message() {
        return "hi, there. this is a message from second service";
    }
}
