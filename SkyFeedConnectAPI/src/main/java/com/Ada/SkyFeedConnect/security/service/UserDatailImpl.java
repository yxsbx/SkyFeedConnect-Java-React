package com.Ada.SkyFeedConnect.security.service;

import com.Ada.SkyFeedConnect.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Custom UserDetails implementation for user authentication.
 */
public class UserDatailImpl implements UserDetails {

    private final String username;

    public UserDatailImpl(String username) {
        super();
        this.username = username;
    }

    public static UserDatailImpl build(User user) {

        return new UserDatailImpl(
            user.getUsername());
        }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

    @Override
    public String getPassword() {
    return null;
  }

    @Override
    public String getUsername() {
    return this.username;
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
    return true;
  }
}