package com.store.user.mappers;

import com.store.user.dtos.user.RegisterUserDTO;
import com.store.user.models.Role;
import com.store.user.models.Roles;
import com.store.user.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public static User fromDTO(RegisterUserDTO dto){
        return new User(
                null,
                dto.getName(),
                dto.getAge(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getPassword(),
                true,
                Arrays.asList(new Role(null, Roles.ROLE_USER, null))
        );
    }
}
