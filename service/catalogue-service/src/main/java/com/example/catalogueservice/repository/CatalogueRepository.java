package com.example.catalogueservice.repository;

import com.example.catalogueservice.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
    Catalogue findByProductId(String productId);
}
