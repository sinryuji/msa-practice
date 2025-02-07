package com.spring_cloud.eureka.client.auth.controller;

import com.spring_cloud.eureka.client.auth.dto.SignRequest;
import com.spring_cloud.eureka.client.auth.dto.UserResponse;
import com.spring_cloud.eureka.client.auth.entity.User;
import com.spring_cloud.eureka.client.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignRequest request) {
        User user = authService.signUp(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new UserResponse(user));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> createAuthToken(@RequestBody SignRequest request) {
        return ResponseEntity.ok(new AuthResponse(authService.signIn(request.getUsername(), request.getPassword())));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String accessToken;
    }
}
