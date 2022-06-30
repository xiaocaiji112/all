package com.study.filter;

import com.study.domain.LoginUser;
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
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        String inner = request.getHeader("inner");
        System.out.println("token = " + token);
        System.out.println("inner = " + inner);
        if(token == null && inner == null){
            filterChain.doFilter(request,response);
            return;
        }
        if(inner != null){
            //整一个内部用户；
        }else {
            //通过redis 查
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("4");
        loginUser.setUsername("666");
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
        filterChain.doFilter(request,response);
    }
}
