package com.example.catalogueservice.service;

import com.example.catalogueservice.dto.ResponseCatalogue;

import java.util.List;

public interface CatalogueService {
    List<ResponseCatalogue> getAllCatalogues();
}
