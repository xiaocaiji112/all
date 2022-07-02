package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class TestController {

    @GetMapping("test")
    //@PreAuthorize("hasAnyAuthority('test')")//使用之前要在配置类中添加开启注解
    @PreAuthorize("@ex.hasAuthority('test')")//使用之前要在配置类中添加开启注解

    public String test(){
        return "66666666666";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('test')")
    public String test2(){
        return "6666222222226666666";
    }
}
