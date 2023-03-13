package com.store.user.services;

import com.store.user.dtos.user.RegisterUserDTO;
import com.store.user.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void saveUser(RegisterUserDTO dto);

    void activeUser(Long id);

    void deactivateUser(Long id);

    User findUserbyId(Long id);

    UserDetails loadUserByUsername(String username);
}
