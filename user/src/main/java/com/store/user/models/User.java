package com.store.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_USER")
@SequenceGenerator(name = "SQ_T_USER", sequenceName = "SQ_T_USER", allocationSize = 1)
public class User implements UserDetails {

    @Id
    @Column(name = "CD_USER", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_T_USER")
    private Long id;

    @Column(name = "NM_USER", nullable = false)
    private String name;

    @Column(name = "DS_AGE", nullable = false)
    private Integer age;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = @JoinColumn(name = "CD_USER",
            foreignKey = @ForeignKey(name = "FK_USER_ADDRESS")),
            inverseJoinColumns = @JoinColumn(name = "CD_ADDRESS",
                    foreignKey = @ForeignKey(name="FK_ADDRESS_USER")))
    private List<Address> address;

    @Column(name = "NR_PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "DS_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "DS_PASSWORD", nullable = false)
    private String password;

    @Column(name = "DS_ACTIVE")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "T_USER_ROLE",
            joinColumns = @JoinColumn(name = "CD_USER"),
            inverseJoinColumns = @JoinColumn(name = "CD_ROLE"))
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword(){return this.password;}

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
