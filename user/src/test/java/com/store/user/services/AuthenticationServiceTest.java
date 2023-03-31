package com.store.user.services;

import com.store.user.mocks.AuthMock;
import com.store.user.mocks.UserMock;
import com.store.user.models.User;
import com.store.user.models.dtos.security.CredentialDto;
import com.store.user.models.dtos.security.Token;
import com.store.user.services.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationServiceImpl service;

    @Mock
    private AuthenticationManager authManager;

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(service,"expiration",expiration);
        ReflectionTestUtils.setField(service,"secret",secret);
        ReflectionTestUtils.setField(service,"issuer",issuer);
    }

    @Test
    public void shouldReturnToken() {
        CredentialDto credentialDto = AuthMock.credential();
        User principal = UserMock.activeUser();
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null);
        Authentication credential = new UsernamePasswordAuthenticationToken(credentialDto.getEmail(), credentialDto.getPassword());
        when(authManager.authenticate(credential)).thenReturn(authentication);
        Token token = service.auth(AuthMock.credential());
        assertNotNull(token);
    }
}
