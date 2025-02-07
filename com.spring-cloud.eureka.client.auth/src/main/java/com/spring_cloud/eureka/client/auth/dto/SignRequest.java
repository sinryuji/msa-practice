package com.spring_cloud.eureka.client.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
