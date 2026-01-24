package com.xworkz.techroute_product_service.entity;


import com.xworkz.techroute_product_service.enums.Status;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class Product extends BaseEntity{


    private String id ;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private Status status;
    private String brand;
    private Map<String, Object> attributes;

}
