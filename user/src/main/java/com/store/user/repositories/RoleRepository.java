package com.store.user.repositories;

import com.store.user.models.Role;
import com.store.user.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(Roles roles);
}
