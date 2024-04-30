package com.Ada.SkyFeedConnect.controller;

import com.Ada.SkyFeedConnect.dto.UserRequestDTO;
import com.Ada.SkyFeedConnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@CrossOrigin
public class UserController {

  private final UserService userService;

    /**
     * Creates a new user based on the provided user data.
     *
     * @param userRequestDTO The DTO containing user data.
     * @return A ResponseEntity indicating the success of the user creation.
     */


  @PostMapping("/newUser")
  public ResponseEntity<String> newUser(@RequestBody UserRequestDTO userRequestDTO) {
    userService.addUser(userRequestDTO);
    return ResponseEntity.created(null).build();
  }
}
