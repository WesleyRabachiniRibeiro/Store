package com.store.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_ADDRESS")
@SequenceGenerator(name = "SQ_ADDRESS", sequenceName = "SQ_ADDRESS", allocationSize = 1)
public class Address {

    @Id
    @JsonIgnore
    @Column(name = "CD_ADDRESS", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ADDRESS")
    private Long id;

    @Column(name = "NM_STREET", nullable = false)
    private String street;

    @Column(name = "NR_ADDRESS", nullable = false)
    private Integer number;

    @Column(name = "NM_CITY", nullable = false)
    private String city;

    @Column(name = "NM_STATE", nullable = false)
    private String state;

    @JsonIgnore
    @ManyToMany(mappedBy = "address")
    private List<User> user;

}
