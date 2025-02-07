package com.spring_cloud.eureka.client.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    public Product(String title, Long price) {
        this.title = title;
        this.price = price;
    }

    public void update(String title, Long price) {
        this.title = title;
        this.price = price;
    }
}
