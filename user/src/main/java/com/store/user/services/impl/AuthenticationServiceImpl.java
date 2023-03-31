package com.store.user.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.store.user.models.dtos.security.CredentialDto;
import com.store.user.models.dtos.security.Token;
import com.store.user.models.User;
import com.store.user.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Token auth(CredentialDto credentialDto) throws AuthenticationException {
        var user = new UsernamePasswordAuthenticationToken(credentialDto.getEmail(), credentialDto.getPassword());
        Authentication authentication = authManager.authenticate(user);
        String token = generateToken(authentication);
        return new Token(token);
    }

    @Override
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(expirationDate)
                .withSubject(principal.getId().toString())
                .sign(Algorithm.HMAC256(secret));
    }
}
