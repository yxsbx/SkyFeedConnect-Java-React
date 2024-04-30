package com.Ada.SFCAuthenticator.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Ada.SFCAuthenticator.model.User;

/**
 * This class implements the UserDetails interface for custom user details.
 */
public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;


    /**
     * Constructs a UserDetailsImpl object with the specified parameters.
     * @param username The username.
     * @param password The password.
     * @param authorities The authorities granted to the user.
     */
    public UserDetailsImpl(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities) {
        super();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Creates a UserDetailsImpl object from a User object.
     * @param user The User object.
     * @return A UserDetailsImpl object created from the User object.
     */
    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(
            user.getLogin(),
            user.getPassword(),
            new ArrayList<>());
    }

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
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
