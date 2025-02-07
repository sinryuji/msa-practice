package com.spring_cloud.eureka.client.product.service;

import com.spring_cloud.eureka.client.product.entity.Product;
import com.spring_cloud.eureka.client.product.repository.ProductRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(@NotBlank String title, @NotBlank Long price) {
        Product product = new Product(title, price);
        return productRepository.save(product);
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return findProductById(id);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("존재하지 않는 상품입니다.")
        );
    }

    @Transactional
    public Product updateProduct(Long id, @NotBlank String title, @NotBlank Long price) {
        Product product = findProductById(id);
        product.update(title, price);
        return product;
    }

    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }
}
