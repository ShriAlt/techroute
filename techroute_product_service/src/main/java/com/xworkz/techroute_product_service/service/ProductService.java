package com.xworkz.techroute_product_service.service;

import com.xworkz.techroute_product_service.dto.ProductRequest;
import com.xworkz.techroute_product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> listProducts();

    ProductResponse getProduct(String id);

    ProductResponse updateProduct(String id, ProductRequest request);

    void deleteProduct(String id);

}
