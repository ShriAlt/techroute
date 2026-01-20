package com.xworkz.techroute_product_service.service;

import com.xworkz.techroute_product_service.dto.ProductRequest;
import com.xworkz.techroute_product_service.dto.ProductResponse;
import com.xworkz.techroute_product_service.entity.Product;
import com.xworkz.techroute_product_service.enums.Status;
import com.xworkz.techroute_product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository =productRepository;
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setCategory(request.category());
        product.setStatus(Status.ACTIVE);
        product.setAttributes(request.attributes());

        Product saved = productRepository.save(product);
        return new ProductResponse(saved.getId(),saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStockQuantity(),
                saved.getCategory(),
                saved.getStatus()
        );
    }
}
