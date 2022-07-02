package com.shop.controller;

import com.google.code.kaptcha.Producer;
import com.shop.config.RedisTemplate;
import com.shop.constant.LoginConstant;
import com.shop.domain.R;
import com.shop.domain.User;
import com.shop.service.LoginService;
import com.shop.service.TokenService;
import com.shop.utils.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("auth")
public class LoginController {

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    @Resource
    RedisTemplate redisTemplete;
    @Autowired
    LoginService loginService;

    @Resource
    TokenService tokenService;

    @GetMapping("login")
    public R<?> login(String name, String password){
        System.out.println(name + password + "===========");
        User user = loginService.login(name, password);
        //这里用jwt把用户名等信息生成token 返回去
        return R.ok(tokenService.createToken(user));
    }

    @GetMapping("logout")
    public R<?> logout(HttpServletRequest request){
        String token = request.getHeader("token");
        //使用jwt解码 token 然后删除redis中的数据
        //第二种方案
        //或者 使用 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //         Object principal = authentication.getPrincipal();
        //得到 用户的数据 然后删掉redis中的数据 你懂的。。。
        return R.ok();
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
        redisTemplete.setObject( LoginConstant.CAPTCHA_UUID+uuid,code,2*60L);
        map.put("img", Base64.encode(os.toByteArray()));
        map.put("uuid", LoginConstant.CAPTCHA_UUID+uuid);
        return ResponseEntity.ok(map);
    }
}
