package com.blog.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    SYS_ADMIN, ADMIN;

    public String getAuthority() {
        return name();
    }

    public static boolean isValidRoles(List<String> roles) {
        List<String> validRoleNames = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toList());
        for (String role: roles) {
            if (!validRoleNames.contains(role)) {
                return false;
            }
        }
        return true;
    }
}
