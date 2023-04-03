package com.store.user.models.dtos.user;

import com.store.user.models.dtos.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {

    private String name;

    private Integer age;

    private List<AddressDTO> address;

    private String phone;

    private String email;

    private String password;
}
