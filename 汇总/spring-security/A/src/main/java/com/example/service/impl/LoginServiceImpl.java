package com.example.service.impl;

import com.example.config.RedisTemplate;
import com.example.entity.User;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public User login(String name,String password) {
        System.out.println("service");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(name,password);

        Authentication authenticate = authenticationManager.
                authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authenticate.getPrincipal();
             redisTemplate.set("user",user.getPermissions().toString(),10*60L);
        return user;
    }

    @Override
    public String logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println(user);

        //TODO 删除redis中的信息根据id

        return "删除了额";
    }
}
