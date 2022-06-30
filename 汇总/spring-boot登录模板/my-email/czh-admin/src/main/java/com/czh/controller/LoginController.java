package com.czh.controller;

import com.czh.common.constant.LoginConstant;
import com.czh.common.constant.StatusConstant;
import com.czh.common.entity.LoginUser;
import com.czh.common.entity.User;
import com.czh.common.exception.loginexception.CaptchaTimeOutException;
import com.czh.common.exception.loginexception.CodeErrorException;
import com.czh.common.exception.loginexception.PasswordErrorException;
import com.czh.common.exception.loginexception.UserNotFoundException;
import com.czh.common.utils.Base64;
import com.czh.system.config.RedisTemplate;
import com.czh.system.service.UserService;
import com.google.code.kaptcha.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
public class LoginController {
    @Resource
    RedisTemplate redisTemplate;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Resource
    UserService userService;

    @PostMapping("/login")
     public ResponseEntity<Map<String ,Object>> login(@RequestBody User user,HttpServletRequest request){

        LoginUser loginUser;

        try {
              loginUser = userService.login(user,request);
        }catch (UserNotFoundException | PasswordErrorException exception){
            exception.printStackTrace();
            return ResponseEntity.status(StatusConstant.USERNAME_PASSWORD_NOT_FOUND).build();
        }catch ( CodeErrorException exception){
            exception.printStackTrace();
            return ResponseEntity.status(StatusConstant.CODE_ERROR).build();
        }catch ( CaptchaTimeOutException e){
            e.printStackTrace();
            return ResponseEntity.status(StatusConstant.CODE_TIME_OUT).build();
        }
        String loginUUID = UUID.randomUUID().toString();
        Set<String> key = redisTemplate.key(LoginConstant.LOGIN_USER_UUID+user.getUserName()+":*");
        for (String s : key) {
            redisTemplate.remove(s);
        }
        redisTemplate.setObject(LoginConstant.LOGIN_USER_UUID+user.getUserName()+":"+loginUUID,loginUser,30*60L);
        Map<String,Object> map = new HashMap<>();
        System.out.println(loginUser);
        map.put("username",loginUser.getUserName());
        map.put("uuid",user.getUserName()+":"+loginUUID);
        return ResponseEntity.status(StatusConstant.SUCCESS).body(map);
    }
    //验证码
    @GetMapping("/captcha")
    public ResponseEntity<Map<String ,String >> getCaptcha(){

        String uuid = UUID.randomUUID().toString();
        String capText = captchaProducerMath.createText();//这个就是验证码 形式： a+b=?@c c为答案
        String capStr = capText.substring(0, capText.lastIndexOf("@"));//a+b=?
        String code = capText.substring(capText.lastIndexOf("@") + 1);//c
        BufferedImage image = captchaProducerMath.createImage(capStr);
        Map<String,String> map = new HashMap<>();

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        redisTemplate.setObject( LoginConstant.CAPTCHA_UUID+uuid,code,2*60L);
        map.put("img", Base64.encode(os.toByteArray()));
        map.put("uuid", LoginConstant.CAPTCHA_UUID+uuid);
        return ResponseEntity.ok(map);
    }
}
