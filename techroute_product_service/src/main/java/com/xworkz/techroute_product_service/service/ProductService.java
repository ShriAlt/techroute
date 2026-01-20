package com.xworkz.techroute_product_service.service;

import com.xworkz.techroute_product_service.dto.ProductRequest;
import com.xworkz.techroute_product_service.dto.ProductResponse;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);
}
