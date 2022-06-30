package com.shop.service.impl;

import com.shop.config.RedisTemplate;
import com.shop.domain.User;
import com.shop.service.LoginService;
import com.shop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    public AuthenticationManager authenticationManager;
    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public User login(String name, String password) {
        System.out.println("service");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(name,password);

        Authentication authenticate = authenticationManager.
                authenticate(usernamePasswordAuthenticationToken);

        return (User) authenticate.getPrincipal();
    }

    @Override
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return null;
    }
}
