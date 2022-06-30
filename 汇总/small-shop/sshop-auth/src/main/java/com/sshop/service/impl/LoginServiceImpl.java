package com.sshop.service.impl;

//import com.sshop.security.config.domain.LoginUser;
import com.sshop.security.config.domain.LoginUser;
import com.sshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Override
    public LoginUser login(LoginUser loginUser) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(authenticate == null){
            return null;
        }
        LoginUser principal =(LoginUser) authenticate.getPrincipal();
        return principal;
    }
}
