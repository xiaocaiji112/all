package com.shop.fillter;


import com.shop.config.RedisTemplate;
import com.shop.domain.User;
import com.shop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


//需要注入到security容器当中
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
        String userKey = JwtUtils.getUserKey(token);
        String userId = JwtUtils.getUserId(token);
        String userName = JwtUtils.getUserName(token);
        System.out.println("userId = " + userId);
        System.out.println("userKey = " + userKey);
        System.out.println("userName = " + userName);
        //完事从redis中取出来LoginUser 这里要比对的
        User user = new User("用户名","密码");
        String  pression = redisTemplate.get(token);
        String substring = "null";
        System.out.println("pression == "+pression);
        if(pression == null||pression.length() == 0 || pression.equals("")){
            substring = "test";
        }else{
            substring =  pression.substring(1, pression.length() - 1);
        }


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
