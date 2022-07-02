package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private String username;
    private String password;
    private List<String> permissions;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean accountNonExpired;
    private List<SimpleGrantedAuthority> authorities;
    private boolean accountNonLocked;
}
