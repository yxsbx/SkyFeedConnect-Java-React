package com.Ada.SFCAuthenticator.controller;

import com.Ada.SFCAuthenticator.dto.AuthenticationDTO;
import com.Ada.SFCAuthenticator.dto.UserRequestDTO;
import com.Ada.SFCAuthenticator.dto.VerifyMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ada.SFCAuthenticator.service.UserService;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller for managing user-related endpoints.
 */
@RestController
@RequestMapping(value = "/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveNewUser(@RequestBody UserRequestDTO user, UriComponentsBuilder uriComponentsBuilder) {
        this.userService.saveNewUser(user);
        var uri = uriComponentsBuilder.path("/users/all").build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody AuthenticationDTO access) {
        this.userService.delete(access);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activate/{uuid}")
    public ResponseEntity<VerifyMessage> verifyUser(@PathVariable String uuid) {
        VerifyMessage verifyMessage = this.userService.verifyUser(uuid);
        return ResponseEntity.ok(verifyMessage);
    }
}
