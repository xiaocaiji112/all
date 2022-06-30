package com.study.service;

import com.study.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {

    @Autowired
    RemoteUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String s = userService.get("inner");
        System.out.println(s);
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("666");
        loginUser.setPassword(s);
        return loginUser;
    }
}
