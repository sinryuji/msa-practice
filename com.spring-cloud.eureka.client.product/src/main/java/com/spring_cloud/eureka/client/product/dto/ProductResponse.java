package com.spring_cloud.eureka.client.product.dto;

import com.spring_cloud.eureka.client.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponse {

    private final Long id;
    private final String title;
    private final Long price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
