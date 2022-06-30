package com.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("get")
    public String get(){
        return "$2a$10$Vyfeyx66L3vPtd8NlpbdZeO0fT69UcMPPC6aJdEsYVwVczE2vVGGW";
    }
}
