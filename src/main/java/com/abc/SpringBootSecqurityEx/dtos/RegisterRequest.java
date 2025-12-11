package com.abc.SpringBootSecqurityEx.dtos;

import java.util.Set;

public record RegisterRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        Set<String> roles
) {}
