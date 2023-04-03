package com.store.user.mocks;

import com.store.user.models.Address;
import com.store.user.models.Role;
import com.store.user.models.User;
import com.store.user.models.dtos.address.AddressDTO;
import com.store.user.models.dtos.user.RegisterUserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMock {

    public static RegisterUserDTO registryUser() {
        List<AddressDTO> address = new ArrayList<>();
        return new RegisterUserDTO("João",16, address,"999999999","joão@email.com","joao123");
    }

    public static User activeUser() {
        List<Address> address = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        return new User(1L,"João",16, address,"999999999","joão@email.com","joão123",true, roles);
    }

    public static User deactivateUser() {
        List<Address> address = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        return new User(1L,"João",16,address,"999999999","joão@email.com","joão123",false, roles);
    }
}
