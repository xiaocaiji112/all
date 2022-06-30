package com.study.controller;

import com.study.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class LoginController {
    @Autowired
    ILoginService loginService;
    @GetMapping("login")
    public String login(String username,String password){
        String login = loginService.login(username, password);
        System.out.println(login);
        return login;
    }
}
