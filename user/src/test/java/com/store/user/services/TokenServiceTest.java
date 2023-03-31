package com.store.user.services;

import com.store.user.services.impl.TokenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TokenServiceTest {

    private final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiU3RvcmUiLCJleHAiOjE2ODAyMDY5NzB9.v67lSDZMysHxfcoO7Ul5-wtogebgchfEvfL8et33od0";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @InjectMocks
    private TokenServiceImpl service;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service,"expiration",expiration);
        ReflectionTestUtils.setField(service,"secret",secret);
        ReflectionTestUtils.setField(service,"issuer",issuer);
    }

    @Test
    public void shouldVerifyToken() {
        boolean verify = service.verifyToken(TOKEN);
        assertTrue(verify);
    }

    @Test
    public void shouldGetUserId() {
        Long userId = service.returnUserId(TOKEN);
        assertNotNull(userId);
    }
}
