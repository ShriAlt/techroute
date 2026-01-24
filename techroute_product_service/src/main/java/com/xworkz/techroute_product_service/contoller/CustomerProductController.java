package com.xworkz.techroute_product_service.contoller;

import com.xworkz.techroute_product_service.dto.ProductResponse;
import com.xworkz.techroute_product_service.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer/products")
public class CustomerProductController {

    private final ProductService productService;

    public CustomerProductController(ProductService service){
        this.productService=service;
    }
    @GetMapping
    public List<ProductResponse> listProducts() {
        return productService.listProducts();
    }
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }
}
