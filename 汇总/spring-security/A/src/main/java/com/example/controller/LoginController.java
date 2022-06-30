package com.example.controller;

import com.example.entity.MyResult;
import com.example.entity.User;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class LoginController {


    @Autowired
    LoginService loginService;

    @GetMapping("/user/login")
    public String  login(String name,String password){
        User user = loginService.login(name, password);
        //这里用jwt把用户名等信息生成token 返回去
        return user.getUsername()+"----------"+user.getPassword();
    }

    @GetMapping("/user/logout")
    public String  logout(){


        return loginService.logout();
    }


}
