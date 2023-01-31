package com.example.firstservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/first-service")
public class FirstServiceController {
    Environment environment;

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
    public String message(HttpServletRequest httpServletRequest) {
        log.info("{}",httpServletRequest.getServerPort());
        return Mono.just(httpServletRequest.getServerPort()).toString();
    }
}
