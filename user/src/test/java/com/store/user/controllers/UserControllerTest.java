package com.store.user.controllers;

import com.store.user.mocks.UserMock;
import com.store.user.models.dtos.user.RegisterUserDTO;
import com.store.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;


@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    @Test
    void shouldSaveUser() throws SQLException {
        doNothing().when(service).saveUser(any(RegisterUserDTO.class));
        var response = controller.saveUser(UserMock.registryUser());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldChangeUserToActive() {
        doNothing().when(service).activeUser(anyLong());
        var response = controller.activeUser(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldChangeUserToDeactivate() {
        doNothing().when(service).deactivateUser(anyLong());
        var response = controller.deactivateUser(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
