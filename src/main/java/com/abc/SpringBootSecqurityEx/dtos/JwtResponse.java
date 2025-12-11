package com.abc.SpringBootSecqurityEx.dtos;



import com.abc.SpringBootSecqurityEx.entity.User;

import java.util.Collection;

public record JwtResponse(
        String jwtToken,
        User user
//        String username,
//        String email,
//        Collection<String> roles
) {}