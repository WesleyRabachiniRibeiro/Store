package com.store.user.services;

import com.store.user.models.User;
import com.store.user.models.dtos.user.RegisterUserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.SQLException;

public interface UserService extends UserDetailsService {

    void saveUser(RegisterUserDTO dto) throws SQLException;

    void activeUser(Long id);

    void deactivateUser(Long id);

    User findUserbyId(Long id);

    UserDetails loadUserByUsername(String username);
}
