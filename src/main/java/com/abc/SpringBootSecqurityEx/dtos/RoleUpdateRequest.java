package com.abc.SpringBootSecqurityEx.dtos;

import java.util.Set;

public record RoleUpdateRequest(Set<String> roles) {
}
