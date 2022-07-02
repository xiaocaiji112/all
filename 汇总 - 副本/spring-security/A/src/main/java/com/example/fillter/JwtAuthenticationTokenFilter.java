package com.example.fillter;

import com.example.config.RedisTemplate;
import com.example.entity.LoginUser;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//需要注入到security容器当中
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("进得来？");
        //获取token
        String token = request.getHeader("token");
        System.out.println("token=="+token);
        if(token == null){
            //放行 进去其他的过滤器
            filterChain.doFilter(request,response);
            //调用会回来的 不可以让他执行下面的 代码哦
            return;
        }
        //解析token

        //完事从redis中取出来LoginUser 这里要比对的
        User user = new User("用户名","密码");
        String  pression = redisTemplate.get(token);
        String substring = "null";

            substring =  pression.substring(1, pression.length() - 1);


        ArrayList<String> list = new ArrayList<>();
        list.add(substring);
        System.out.println(substring);
        user.setPermissions(list);
        //将LoginUser存入 SecurityContextHolder
        //三个参数的构造器里面的已认证是true
        //TODO 传进去权限信息哦

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken //这个传进去的是从redis中取出来的对象哦
                = new UsernamePasswordAuthenticationToken(
                        user
                ,null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
