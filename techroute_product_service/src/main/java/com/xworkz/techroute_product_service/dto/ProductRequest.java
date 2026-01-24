package com.xworkz.techroute_product_service.dto;

import java.util.Map;

import com.xworkz.techroute_product_service.enums.Status;
import jakarta.validation.constraints.*;

public record ProductRequest(

        @NotBlank(message = "Product name is required")
        @Size(max = 100, message = "Product name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Stock quantity is required")
        @Min(value = 0, message = "Stock quantity must be non-negative")
        Integer stockQuantity,

        @NotBlank(message = "Category is required")
        @Size(max = 50, message = "Category must not exceed 50 characters")
        String category,

        Map<String, Object> attributes,

        @NotBlank(message = "Product code is required")
        @Pattern(regexp = "^[A-Z0-9_-]{6,20}$", message = "Product code must be alphanumeric, 6–20 chars")
        String productCode,

        @NotBlank(message = "Brand is required")
        String brand

//        @NotBlank(message = "Status is required")
//        @Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be ACTIVE or INACTIVE")
//        Status status
) {}

