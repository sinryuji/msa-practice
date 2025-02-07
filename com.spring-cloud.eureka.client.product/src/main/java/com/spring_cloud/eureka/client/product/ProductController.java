package com.spring_cloud.eureka.client.product;

import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ProductController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${message}")
    private String message;

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable int id) {
        return "Product " + id + " info!!!!! From port : " + serverPort + " and Message : " + message;
    }
}
