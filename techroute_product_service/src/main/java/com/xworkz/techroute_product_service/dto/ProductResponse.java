package com.xworkz.techroute_product_service.dto;

import com.xworkz.techroute_product_service.enums.Status;

public record ProductResponse(String id,
                              String name,
                              String description,
                              Double price,
                              Integer stockQuantity,
                              String category,
                              Status status,
                              String brand,
                              String createdAt,
                              String createdBy) {
}
