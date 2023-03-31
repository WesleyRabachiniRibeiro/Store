package com.store.user.services;


import com.store.user.mocks.UserMock;
import com.store.user.models.User;
import com.store.user.repositories.UserRepository;
import com.store.user.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Test
    public void shouldSaveTheUser() throws Exception {
        when(repository.save(any(User.class))).thenReturn(any(User.class));
        service.saveUser(UserMock.registryUser());
        verify(repository).save(any(User.class));
    }

    @Test
    public void shouldActivateUser() {
        User user = UserMock.deactivateUser();
        when(repository.save(user)).thenReturn(user);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        service.activeUser(1l);
        assertTrue(user.isActive());
        verify(repository).save(any(User.class));
    }

    @Test
    public void shouldDeactivateUser() {
        User user = UserMock.activeUser();
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        service.deactivateUser(1l);
        assertFalse(user.isActive());
        verify(repository).save(any(User.class));
    }

    @Test
    public void shouldReturnUser() {
        when(repository.findById(1L)).thenReturn(Optional.of(UserMock.activeUser()));
        User user = service.findUserbyId(1L);
        assertNotNull(user);
        verify(repository).findById(1L);
    }
}
