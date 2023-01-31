package com.example.catalogueservice.service;

import com.example.catalogueservice.dto.ResponseCatalogue;
import com.example.catalogueservice.entity.Catalogue;
import com.example.catalogueservice.mapper.CatalogueResponseMapper;
import com.example.catalogueservice.repository.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogueServiceImpl implements CatalogueService {
    private final CatalogueRepository catalogueRepository;
    private final CatalogueResponseMapper catalogueResponseMapper;

    @Override
    public List<ResponseCatalogue> getAllCatalogues() {
        List<Catalogue> catalogues = catalogueRepository.findAll();
        return catalogueResponseMapper.of(catalogues);
    }
}
