package com.example.config;

import com.example.fillter.JwtAuthenticationTokenFilter;
import com.example.handler.AccessDeniedHandlerImpl;
import com.example.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//这个是开启 权限哦
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtAuthenticationTokenFilter authenticationTokenFilter;
    @Bean
    public PasswordEncoder passwordEncoder (){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;//认证异常
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler; // 授权异常
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable().
                //不通过Session获取SecurityContext
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口允许匿名访问
                .antMatchers("/user/login").anonymous()
                //出上面以外的所有请求全部需要鉴全认证
                .anyRequest().authenticated();
        //添加过滤器
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //添加异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        //允许跨域
        /*
        * 跨域总览：
        * 首先你需要开启整体的 @CrossOrigin 然后开启此http.cors(); 不开启后者的话认证授权的异常不会被捕获到
        * 如果你 使用了授权 那么如果授权匹配了 就不会呗拦截
        * */
       http.cors();
    }
}
