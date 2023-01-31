package com.example.catalogueservice.controller;

import com.example.catalogueservice.dto.ResponseCatalogue;
import com.example.catalogueservice.service.CatalogueService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogue-service")
public class CatalogueRestController {
    private final Environment environment;
    private final CatalogueService catalogueService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in user service %s",
                environment.getProperty("local.server.port"));
    }

    @GetMapping("/catalogues")
    public ResponseEntity<List<ResponseCatalogue>> getCatalogues() {
        List<ResponseCatalogue> response = catalogueService.getAllCatalogues();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
