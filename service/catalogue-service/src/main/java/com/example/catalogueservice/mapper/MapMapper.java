package com.example.catalogueservice.mapper;

import org.mapstruct.Mapper;

import java.util.HashMap;

@Mapper(componentModel = "spring")
public interface MapMapper {
    HashMap<Object, Object> of(String json);
}
