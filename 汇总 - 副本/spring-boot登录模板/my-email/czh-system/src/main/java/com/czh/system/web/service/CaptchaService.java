package com.czh.system.web.service;

import com.czh.common.exception.loginexception.CaptchaTimeOutException;
import com.czh.system.config.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class CaptchaService {
    @Resource
    RedisTemplate redisTemplate;

    public boolean verifyCode(String uuid, String code) {
        String realCode = redisTemplate.get(uuid);
        if(realCode == null){
        throw new CaptchaTimeOutException("验证码过期");
        }else {
            String substring = realCode.substring(1, realCode.length() - 1);
            return Objects.equals(code, substring);
        }

    }
}
