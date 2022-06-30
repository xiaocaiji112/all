package com.shop.service;

import com.shop.domain.User;

public interface LoginService {
    User login(String name, String password);

    String logout();
}
