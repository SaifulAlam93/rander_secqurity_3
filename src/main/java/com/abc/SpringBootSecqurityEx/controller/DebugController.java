package com.abc.SpringBootSecqurityEx.controller;
import com.abc.SpringBootSecqurityEx.dtos.AdminStatistics;
import com.abc.SpringBootSecqurityEx.dtos.RoleCreateRequest;
import com.abc.SpringBootSecqurityEx.dtos.RoleUpdateRequest;
import com.abc.SpringBootSecqurityEx.entity.Role;
import com.abc.SpringBootSecqurityEx.entity.User;
import com.abc.SpringBootSecqurityEx.repository.RoleRepository;
import com.abc.SpringBootSecqurityEx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/debug")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DebugController {

    @GetMapping("/my-roles")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getMyRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> debugInfo = new HashMap<>();
        debugInfo.put("username", auth.getName());
        debugInfo.put("authorities", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        debugInfo.put("authenticated", auth.isAuthenticated());

        return debugInfo;
    }
}