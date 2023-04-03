package com.store.user.services.impl;

import com.store.user.exception.StatusException;
import com.store.user.mappers.UserMapper;
import com.store.user.models.Role;
import com.store.user.models.User;
import com.store.user.models.dtos.cep.CepDTO;
import com.store.user.models.dtos.user.RegisterUserDTO;
import com.store.user.repositories.RoleRepository;
import com.store.user.repositories.UserRepository;
import com.store.user.services.CepService;
import com.store.user.services.RoleService;
import com.store.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CepService cepService;

    @Autowired
    private RoleService roleService;

    @Override
    public void saveUser(RegisterUserDTO dto) throws SQLException {
        try {
            List<CepDTO> cepList = cepService.getCep(dto.getAddress());
            User user = UserMapper.fromDTO(dto,cepList);
            repository.save(roleService.verifyExistRole(user));
        }catch (Exception ex) {
            if (ex.getMessage() == "Invalid CEP") {
                throw new RuntimeException(ex.getMessage());
            }else {
                throw new SQLException("Not is possible save this user");
            }
        }
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
