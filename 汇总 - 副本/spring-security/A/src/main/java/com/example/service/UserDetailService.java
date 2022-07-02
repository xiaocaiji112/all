package com.example.service;

import com.example.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void a(){
        //$2a$10$x4Za6hWFeKf1.kBY.8DINOwc8zDGpRQ34U39TNWIYDQG5PDnAMOKK
      //  $2a$10$Vyfeyx66L3vPtd8NlpbdZeO0fT69UcMPPC6aJdEsYVwVczE2vVGGW
      //  $2a$10$kegL24cHipOS6reH6c9xBeJdfgNINcSQ7DH4vW/yPlWk7DlHtqjfu
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("666");
        String encode2 = passwordEncoder.encode("666");

        System.out.println(encode);
        System.out.println(encode2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息 数据库哦 你懂的权限的都要查出来
        System.out.println("UserDetails");
        System.out.println(username);
        User userDetails =  new User(username, "$2a$10$Vyfeyx66L3vPtd8NlpbdZeO0fT69UcMPPC6aJdEsYVwVczE2vVGGW");
        ArrayList<String > list = new ArrayList<>();
        list.add("test"); //权限
        userDetails.setPermissions(list);
        return userDetails;
    }
}
