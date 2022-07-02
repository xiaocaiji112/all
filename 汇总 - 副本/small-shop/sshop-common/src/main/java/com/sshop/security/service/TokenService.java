package com.sshop.security.service;

import com.sshop.core.constant.RedisConstant;
import com.sshop.core.constant.SecurityConstant;
import com.sshop.core.utils.JwtUtils;
import com.sshop.security.config.domain.LoginUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenService {
    public Map<String, Object> createToken(LoginUser loginUser){
        String token = UUID.randomUUID().toString();
        String username = loginUser.getUsername();
        refresh(loginUser,token);
        Map<String,Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstant.USER_KEY,token);
        claimsMap.put(SecurityConstant.USER_NAME,username);
        Map<String ,Object> rspMap = new HashMap<>();
        rspMap.put("token", JwtUtils.createToken(claimsMap));
        return rspMap;
    }

    private void refresh(LoginUser loginUser,String token) {
        String rediskey  = RedisConstant.LOGIN_USER_PREFIX +loginUser.getUsername()+":"+token;
        //TODO 存redis 刷新应该是
    }
}
