package com.example.service;

import com.example.entity.User;

public interface LoginService {
    User login(String name,String password);

    String logout();
}
