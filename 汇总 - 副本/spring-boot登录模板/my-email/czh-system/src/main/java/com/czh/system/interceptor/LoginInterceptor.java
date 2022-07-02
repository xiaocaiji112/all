package com.czh.system.interceptor;

import com.czh.common.constant.LoginConstant;
import com.czh.common.entity.LoginUser;
import com.czh.system.config.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("我进拦截器啦");
        String authorV = request.getHeader("AuthorV");
        if (authorV == null) {
            return false;
        } else {
            LoginUser loginUser = redisTemplate.getObject(LoginConstant.LOGIN_USER_UUID+authorV, LoginUser.class);
            if(loginUser == null){
                return false;
            }
            return true;
        }
    }
}