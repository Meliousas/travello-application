package com.example.travello.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Column(columnDefinition = "varchar(8000)")
    private String description;

    @NotNull
    private boolean isAdmin;

    @NotNull
    private boolean isBusiness;

    @OneToMany(targetEntity=Trip.class, mappedBy="account", fetch=FetchType.EAGER)
    private List<Trip> trips;

    public Account(String username, String password, String email, boolean isBusiness) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isBusiness = isBusiness;
    }


}
