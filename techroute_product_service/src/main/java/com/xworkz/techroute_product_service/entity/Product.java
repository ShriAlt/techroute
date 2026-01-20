package com.xworkz.techroute_product_service.entity;


import com.xworkz.techroute_product_service.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collation = "products")
public class Product extends BaseEntity{


    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private Status status;
    private Map<String, Object> attributes;

}
