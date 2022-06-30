package com.security.config;

import com.security.controller.handler.MySuccessHandler;
import com.security.controller.handler.MyUnSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MySuccessHandler mySuccessHandler;
    @Autowired
    AuthenticationFailureHandler myUnSuccessHandler;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(mySuccessHandler)
                        .failureHandler(myUnSuccessHandler);
        http.logout().logoutSuccessUrl("https:www.baidu.com").logoutSuccessHandler(logoutSuccessHandler);

        http.authorizeRequests().anyRequest().authenticated();

    }
}
