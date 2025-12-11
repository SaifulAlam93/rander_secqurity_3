package com.abc.SpringBootSecqurityEx.dtos;

import com.abc.SpringBootSecqurityEx.entity.Role;

import java.util.List;

public record AdminStatistics(long totalUsers, long enabledUsers, List<Role> roles) {
}
