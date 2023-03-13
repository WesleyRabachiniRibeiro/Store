package com.store.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ROLE",
        uniqueConstraints = {@UniqueConstraint(name = "UN_ROLE",
                columnNames = {"NM_ROLE"})})
@SequenceGenerator(name = "SQ_ROLE", sequenceName = "SQ_ROLE", allocationSize = 1)
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "CD_ROLE", length = 3, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NM_ROLE", length = 30, unique = true)
    private Roles name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.DETACH)
    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return this.name.toString();
    }

}
