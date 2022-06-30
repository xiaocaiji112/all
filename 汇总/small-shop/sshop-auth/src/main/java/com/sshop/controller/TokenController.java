package com.sshop.controller;

import com.sshop.security.config.domain.LoginUser;
import com.sshop.core.domain.R;
import com.sshop.security.service.TokenService;
import com.sshop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class TokenController {
    @Autowired
    LoginService loginService;

    @Autowired
    TokenService tokenService;
    @GetMapping("test")
    public String test(){


        return "test";
    }
    @PostMapping("login")
    public R login(@RequestBody(required = false) LoginUser loginUser){
        System.out.println(loginUser);
        LoginUser loginUser1 = loginService.login(loginUser);
        return R.success().setData(tokenService.createToken(loginUser1));
    }
}
