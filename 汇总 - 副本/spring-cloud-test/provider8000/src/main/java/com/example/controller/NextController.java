package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider2")
public class NextController {
    @GetMapping("test")
    public String test2(){
        return "666";
    }
}
