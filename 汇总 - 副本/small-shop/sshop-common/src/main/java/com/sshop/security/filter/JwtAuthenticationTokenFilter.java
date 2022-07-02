package com.sshop.security.filter;

import com.sshop.core.constant.RedisConstant;
import com.sshop.core.constant.SecurityConstant;
import com.sshop.core.utils.JwtUtils;
import com.sshop.security.config.domain.LoginUser;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("过滤器");
        String token = request.getHeader("token");

        if (token == null){
            System.out.println("你没有token");
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        Claims claims = JwtUtils.parseToken(token);
        String rediskey = RedisConstant.LOGIN_USER_PREFIX +claims.get(SecurityConstant.USER_NAME)+":"+claims.get(SecurityConstant.USER_KEY);
        //TODO 查询redis 拿出 loginuser;
        System.out.println(rediskey);
        LoginUser loginUser = new LoginUser();
        loginUser.setPassword("666");
        loginUser.setUsername("999");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken //这个传进去的是从redis中取出来的对象哦
                = new UsernamePasswordAuthenticationToken(
                loginUser
                ,null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
