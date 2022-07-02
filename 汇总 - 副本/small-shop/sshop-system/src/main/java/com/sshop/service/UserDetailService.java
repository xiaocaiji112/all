package com.sshop.service;



import com.sshop.security.config.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {



    @Autowired
    UserService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //TODO 查数据库使用名字封装一个UserDetails


        //System.out.println("查出来的"+user);
        System.out.println(username);
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("username");
        loginUser.setPassword("$2a$10$Vyfeyx66L3vPtd8NlpbdZeO0fT69UcMPPC6aJdEsYVwVczE2vVGGW");
        return loginUser;
    }
}
