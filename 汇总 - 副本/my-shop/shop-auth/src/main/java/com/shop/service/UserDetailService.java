package com.shop.service;


import com.shop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息 数据库哦 你懂的权限的都要查出来
        System.out.println("UserDetails");
        System.out.println(username+"=============================");
        User userDetails =  new User(username,
                "$2a$10$Vyfeyx66L3vPtd8NlpbdZeO0fT69UcMPPC6aJdEsYVwVczE2vVGGW");
        ArrayList<String > list = new ArrayList<>();
        list.add("test"); //权限
        userDetails.setPermissions(list);
        return userDetails;
    }
}
