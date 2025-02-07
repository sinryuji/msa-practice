package com.spring_cloud.eureka.client.product.controller;

import com.spring_cloud.eureka.client.product.dto.ProductRequest;
import com.spring_cloud.eureka.client.product.dto.ProductResponse;
import com.spring_cloud.eureka.client.product.entity.Product;
import com.spring_cloud.eureka.client.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Product product = productService.createProduct(request.getTitle(), request.getPrice());
        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProductList() {
        return ResponseEntity.ok(productService.getProductList().stream().map(ProductResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductResponse(productService.getProductById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(new ProductResponse(productService.updateProduct(id, request.getTitle(), request.getPrice())));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Product deleted successfully");
    }
}
