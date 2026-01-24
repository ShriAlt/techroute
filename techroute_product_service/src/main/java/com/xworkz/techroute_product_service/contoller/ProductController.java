package com.xworkz.techroute_product_service.contoller;

import com.xworkz.techroute_product_service.dto.ProductRequest;
import com.xworkz.techroute_product_service.dto.ProductResponse;
import com.xworkz.techroute_product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    public ProductController(ProductService productService){
        this.service=productService;
    }
    private final ProductService service;

    @GetMapping
    public List<ProductResponse> listProducts() {
        return service.listProducts();
    }
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return service.addProduct(request);
    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable String id, @RequestBody ProductRequest request) {
        return service.updateProduct(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
    }
}

