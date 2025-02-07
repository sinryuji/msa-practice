package com.spring_cloud.eureka.client.auth.service;

import com.spring_cloud.eureka.client.auth.entity.User;
import com.spring_cloud.eureka.client.auth.entity.UserRoleEnum;
import com.spring_cloud.eureka.client.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long expiration;

    @Value("${service.jwt.secret-key}")
    private String key;

    private SecretKey secretKey;

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
    }

    public String createAccessToken(String username, UserRoleEnum role) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role.toString())
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

    public User signUp(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("already signed up");
        } else {
            User user = new User(username, password, UserRoleEnum.MEMBER);
            return userRepository.save(user);
        }
    }

    public String signIn(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        return createAccessToken(user.getUsername(), user.getRole());
    }
}

