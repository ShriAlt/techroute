package com.xworkz.techroute_product_service.service;

import com.xworkz.techroute_product_service.dto.ProductRequest;
import com.xworkz.techroute_product_service.dto.ProductResponse;
import com.xworkz.techroute_product_service.entity.Product;
import com.xworkz.techroute_product_service.enums.Status;
import com.xworkz.techroute_product_service.exception.ProductNotFoundException;
import com.xworkz.techroute_product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        product.setBrand(request.brand());
        product.setAttributes(request.attributes());
        Product saved = productRepository.save(product);

        return new ProductResponse(saved.getId(),saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStockQuantity(),
                saved.getCategory(),
                saved.getStatus(),
                saved.getBrand(),saved.getCreatedAt().toString(),saved.getCreatedBy()
        );
    }

    @Override
    public List<ProductResponse> listProducts() {

        return productRepository.findByStatus(Status.ACTIVE.toString())
                .stream().map(this::mapToResponse).toList();
    }


    @Override
    public ProductResponse updateProduct(String id, ProductRequest request) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
            product.setName(request.name());
            product.setDescription(request.description());
            product.setPrice(request.price());
            product.setStockQuantity(request.stockQuantity());
            product.setCategory(request.category());
            productRepository.save(product);
            return mapToResponse(product);
        }

        @Override
        public void deleteProduct(String id) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
            product.setStatus(Status.INACTIVE);
            product.setIsDeleted(true);
            productRepository.save(product);
        }



    @Override
    public ProductResponse getProduct(String id) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
            return mapToResponse(product);
    }


        private ProductResponse mapToResponse(Product product) {
            return new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getCategory(),
                    product.getStatus(),
                    product.getBrand(),
                    product.getCreatedAt().toString(), product.getCreatedBy()
            );
        }


}

