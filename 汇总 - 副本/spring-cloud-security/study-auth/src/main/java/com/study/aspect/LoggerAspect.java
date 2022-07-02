package com.study.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @Before("execution(public String com.study.service.impl.LoginServiceImpl.*(..))")
    public void before(JoinPoint joinpoint){
        String name = joinpoint.getSignature().getName();
        System.out.println(name + "账号密码信息"+ Arrays.toString(joinpoint.getArgs()));

    }
    @AfterReturning(value = "execution(public String com.study.service.impl.LoginServiceImpl.*(..))",returning = "result")
    public void after(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "返沪的信息是"+result);
    }
}



