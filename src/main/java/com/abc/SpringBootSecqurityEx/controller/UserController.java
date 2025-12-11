package com.abc.SpringBootSecqurityEx.controller;
import com.abc.SpringBootSecqurityEx.dtos.StatusUpdateRequest;
import com.abc.SpringBootSecqurityEx.dtos.UserUpdateRequest;
import com.abc.SpringBootSecqurityEx.entity.User;
import com.abc.SpringBootSecqurityEx.repository.UserRepository;
import com.abc.SpringBootSecqurityEx.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    // GET ALL USERS - Only accessible by ADMIN
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // GET USER BY USERNAME - Accessible by ADMIN or the user themselves
    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE USER - Accessible by ADMIN or the user themselves
    @PutMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public ResponseEntity<User> updateUser(
            @PathVariable String username,
            @RequestBody UserUpdateRequest updateRequest) {

        Optional<User> existingUser = userRepository.findByUserName(username);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = existingUser.get();

        // Only allow updating specific fields
        if (updateRequest.firstName() != null) {
            user.setUserFirstName(updateRequest.firstName());
        }
        if (updateRequest.lastName() != null) {
            user.setUserLastName(updateRequest.lastName());
        }
        if (updateRequest.email() != null) {
            user.setEmail(updateRequest.email());
        }

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE USER - Only accessible by ADMIN
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (!userRepository.existsById(username)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(username);
        return ResponseEntity.ok("User deleted successfully");
    }

    // ENABLE/DISABLE USER - Only accessible by ADMIN
    @PatchMapping("/{username}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUserStatus(
            @PathVariable String username,
            @RequestBody StatusUpdateRequest statusRequest) {

        Optional<User> existingUser = userRepository.findByUserName(username);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = existingUser.get();
        user.setEnabled(statusRequest.enabled());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}

