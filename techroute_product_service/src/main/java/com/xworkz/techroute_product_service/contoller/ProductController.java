package com.xworkz.techroute_product_service.contoller;

import com.xworkz.techroute_product_service.dto.ProductRequest;
import com.xworkz.techroute_product_service.dto.ProductResponse;
import com.xworkz.techroute_product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("addProduct")
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request); }
}
