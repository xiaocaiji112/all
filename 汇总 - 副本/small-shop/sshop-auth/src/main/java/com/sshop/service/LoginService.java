package com.sshop.service;

import com.sshop.security.config.domain.LoginUser;

public interface LoginService {
    LoginUser login(LoginUser loginUser);
}
