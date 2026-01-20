package com.xworkz.techroute_product_service.repository;

import com.xworkz.techroute_product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {

    List<Product> findByCategory(String category);
    List<Product> findByStatus(String status);
}
