package com.study.service.impl;

import com.study.domain.LoginUser;
import com.study.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                 = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        System.out.println((authenticate == null) + "===");
        LoginUser principal = (LoginUser)authenticate.getPrincipal();
       return principal.getUsername()+principal.getPassword();
    }
}
