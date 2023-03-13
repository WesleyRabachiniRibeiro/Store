package com.store.user.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.store.user.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Override
    public boolean verifyToken(String token) {
        try {
            if(token == null) return false;
            JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build().verify(token);
            return true;
        }catch (JWTVerificationException exception) {
            return false;
        }
    }

    @Override
    public Long returnUserId(String token) {
        String subject = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build().verify(token).getSubject();
        return Long.parseLong(subject);
    }
}
