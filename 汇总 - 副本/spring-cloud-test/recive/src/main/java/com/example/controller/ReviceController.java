package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviceController {
    @PostMapping("warning")
    public void a(@RequestBody String waringMsg){
        System.out.println("警告");
        System.out.println(waringMsg);
    }
}
