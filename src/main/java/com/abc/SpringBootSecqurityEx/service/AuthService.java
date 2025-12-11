package com.abc.SpringBootSecqurityEx.service;

import com.abc.SpringBootSecqurityEx.dtos.JwtResponse;
import com.abc.SpringBootSecqurityEx.dtos.LoginRequest;
import com.abc.SpringBootSecqurityEx.dtos.RegisterRequest;
import com.abc.SpringBootSecqurityEx.entity.Role;
import com.abc.SpringBootSecqurityEx.entity.User;
import com.abc.SpringBootSecqurityEx.repository.RoleRepository;
import com.abc.SpringBootSecqurityEx.repository.UserRepository;
import com.abc.SpringBootSecqurityEx.secqurity.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken(loginRequest.username());

        User user = userRepository.findByUserName(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

//        Collection<String> roles = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                user
        );
    }

    public String register(RegisterRequest registerRequest) {
        validateRegistration(registerRequest);

        User user = createUserFromRequest(registerRequest);
        setUserRoles(user, registerRequest.roles());

        userRepository.save(user);
        return "User registered successfully";
    }

    public String initializeRoles() {
        String[] defaultRoles = {"USER", "ADMIN", "MODERATOR"};
        for (String roleName : defaultRoles) {
            if (!roleRepository.existsById(roleName)) {
                Role role = new Role();
                role.setRoleName(roleName);
                role.setRoleDescription(roleName + " Role");
                roleRepository.save(role);
            }
        }
        return "Roles initialized successfully";
    }

    private void validateRegistration(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private User createUserFromRequest(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());
        user.setUserFirstName(registerRequest.firstName());
        user.setUserLastName(registerRequest.lastName());

        // Set default values
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);

        return user;
    }

    private void setUserRoles(User user, Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();

        if (roleNames == null || roleNames.isEmpty()) {
            // Default role
            Role userRole = roleRepository.findByRoleName("USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role USER not found."));
            roles.add(userRole);
        } else {
            roleNames.forEach(roleName -> {
                Role foundRole = roleRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Error: Role " + roleName + " not found."));
                roles.add(foundRole);
            });
        }

        user.setRoles(roles);
    }
}