package com.jaiswarup.stocky.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final MyUser myUser;

    public UserPrincipal(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public String getUsername() {
        return myUser.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return myUser.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return myUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return myUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return myUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return myUser.isEnabled();
    }
}
