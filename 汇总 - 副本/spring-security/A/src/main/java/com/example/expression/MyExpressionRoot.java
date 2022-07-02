package com.example.expression;

import com.example.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("ex")
public class MyExpressionRoot {


    public final boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ArrayList<String> permissions = user.getPermissions();

        //判断用户权限集合中是否存在 authority
        return permissions.contains(authority);
    }
}
