package com.example.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.feign.MyFeign;

import com.example.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
@RefreshScope // 开启配置更新
public class TestController {
    @Autowired
    MyFeign myFeign;
    @Autowired
    RemoteService remoteService;

    @Value("${test}")
    String test;

    @GetMapping("test")
    @SentinelResource(value = "test",blockHandler = "test_block",
                                      fallback = "test_fallback")
    public String test(){
        double random = Math.random();
        if(random > 0.5){
            int x = 1/0;
        }
        return "HaHaHa!!!" + test;
    }
    //block热点的错误才会  如果是逻辑的就不会去降级
    public String test_block(BlockException e){

        return "对不起你被限流了";
    }
    //fallback是逻辑错误  如果是限流的就不会去降级
    public String test_fallback(){

        return "对不起你出现错误了";
    }
    @GetMapping("testfeign")
    public String testfeign(){
        System.out.println(myFeign.test2());
        return myFeign.test();
    }
    @GetMapping("testremotefeign")
    public String testremotefeign(){
        System.out.println(remoteService.test2());
        return remoteService.test();
    }
}
