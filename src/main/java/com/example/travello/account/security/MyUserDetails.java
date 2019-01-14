package com.example.travello.account.security;

import com.example.travello.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Long userId;
    private Boolean isAdmin;
    private Boolean isBusiness;
    private boolean isActive;

    public MyUserDetails(Account account) {
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.userId = account.getId();

        List<GrantedAuthority> auths = new ArrayList<>();

        auths.add(new SimpleGrantedAuthority("USER"));
        if(account.isAdmin()) {auths.add(new SimpleGrantedAuthority("ADMIN"));}
        if(account.isBusiness()) {auths.add(new SimpleGrantedAuthority("BUSINESS"));}

        this.authorities = auths;
        this.isAdmin = account.isAdmin();
        this.isBusiness = account.isBusiness();
        this.isActive = account.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Boolean getBusiness() {
        return isBusiness;
    }

    public Long getUserId() {
        return userId;
    }

}
