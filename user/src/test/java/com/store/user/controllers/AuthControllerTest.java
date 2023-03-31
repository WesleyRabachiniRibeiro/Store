package com.store.user.controllers;


import com.store.user.mocks.AuthMock;
import com.store.user.models.dtos.security.CredentialDto;
import com.store.user.services.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthControllerTest {

    @InjectMocks
    private AuthController controller;

    @Mock
    private AuthenticationService service;

    @Test
    void shouldAuthenticateUser() {
        when(service.auth(any(CredentialDto.class))).thenReturn(AuthMock.token());
        var response = controller.authorize(AuthMock.credential());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
