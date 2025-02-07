package com.spring_cloud.eureka.client.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProductRequest {

    @NotBlank
    private String title;

    @NotBlank
    private Long price;
}
