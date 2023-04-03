package com.store.user.mappers;

import com.store.user.models.Address;
import com.store.user.models.Role;
import com.store.user.models.Roles;
import com.store.user.models.User;
import com.store.user.models.dtos.cep.CepDTO;
import com.store.user.models.dtos.user.RegisterUserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public static User fromDTO(RegisterUserDTO dto, List<CepDTO> cepList){
        List<Address> address = new ArrayList<>();
        IntStream.range(0, cepList.toArray().length).forEach(idx -> {
            address.add(
                    new Address(null, cepList.get(idx).getPlace(), dto.getAddress().get(idx).getNumber(), cepList.get(idx).getLocale(), cepList.get(idx).getUf(), null));
        });
        return new User(
                null,
                dto.getName(),
                dto.getAge(),
                address,
                dto.getPhone(),
                dto.getEmail(),
                dto.getPassword(),
                true,
                Arrays.asList(new Role(null, Roles.ROLE_USER, null))
        );
    }
}
