package com.store.user.services.impl;

import com.store.user.exception.StatusException;
import com.store.user.dtos.user.RegisterUserDTO;
import com.store.user.mappers.UserMapper;
import com.store.user.models.User;
import com.store.user.repositories.UserRepository;
import com.store.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void saveUser(RegisterUserDTO dto){
        repository.save(UserMapper.fromDTO(dto));
    }

    @Override
    public void activeUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        if (user.isActive()) {
            throw new StatusException(user.isActive());
        }
        user.setActive(true);
        repository.save(user);
    }

    @Override
    public void deactivateUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        if (!user.isActive()) {
            throw new StatusException(user.isActive());
        }
        user.setActive(false);
        repository.save(user);
    }

    @Override
    public User findUserbyId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));
    }
}
