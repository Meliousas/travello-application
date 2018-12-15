package com.example.travello.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;

    private String photo;
    private String description;

    @NotNull
    private boolean isAdmin;

    @NotNull
    private boolean isBusiness;

    @OneToMany(targetEntity=Trip.class, mappedBy="account", fetch=FetchType.EAGER)
    private List<Trip> trips;

    @OneToMany( cascade=CascadeType.ALL)
    private List<Role> roles;

    public Account(String username, String password, String email, boolean isBusiness, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isBusiness = isBusiness;
        this.roles = roles;
    }


}
