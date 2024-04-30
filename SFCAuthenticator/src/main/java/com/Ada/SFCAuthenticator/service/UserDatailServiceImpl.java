package com.Ada.SFCAuthenticator.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ada.SFCAuthenticator.model.User;
import com.Ada.SFCAuthenticator.repository.UserRepository;

/**
 * This service class implements the UserDetailsService for user authentication.
 */
@Service
@RequiredArgsConstructor
public class UserDatailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads user details by username for authentication.
     * @param username The username to load user details for.
     * @return The UserDetails object for the specified username.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByLogin(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found"));
        return UserDetailsImpl.build(user);
    }
}
