package com.xworkz.techroute_product_service.dto;

import java.util.Map;

public record ProductRequest (
        String name,
        String description,
        Double price,
        Integer stockQuantity,
        String category, Map<String, Object> attributes
    ){
}
