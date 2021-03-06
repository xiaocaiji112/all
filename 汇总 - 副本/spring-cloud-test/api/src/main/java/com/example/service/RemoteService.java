package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("provider")
public interface RemoteService {
    @GetMapping("/provider/test")
    String test();
    @GetMapping("/provider2/test")
    String test2();
}
