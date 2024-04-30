package com.Ada.SkyFeedConnect.service;

import com.Ada.SkyFeedConnect.dto.UserRequestDTO;
import com.Ada.SkyFeedConnect.model.User;
import com.Ada.SkyFeedConnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Adds a new user to the system.
     *
     * @param userRequestDTO The DTO containing user data.
     */

    public void addUser(UserRequestDTO userRequestDTO) {
  userRepository.save(new User(userRequestDTO.username()));
  }
}
