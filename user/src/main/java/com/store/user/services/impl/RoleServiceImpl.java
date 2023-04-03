package com.store.user.services.impl;

import com.store.user.models.Role;
import com.store.user.models.User;
import com.store.user.repositories.RoleRepository;
import com.store.user.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public User verifyExistRole(User user) {
        user.getRoles().forEach(role -> {
            Role roleExist = repository.findByName(role.getName());
            if(roleExist != null){
                user.getRoles().set(user.getRoles().indexOf(role), roleExist);
            }
        });
        return user;
    }
}
