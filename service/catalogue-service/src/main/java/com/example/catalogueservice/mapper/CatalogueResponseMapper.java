package com.example.catalogueservice.mapper;

import com.example.catalogueservice.dto.ResponseCatalogue;
import com.example.catalogueservice.entity.Catalogue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogueResponseMapper {
    List<ResponseCatalogue> of (List<Catalogue> catalogues);
}
