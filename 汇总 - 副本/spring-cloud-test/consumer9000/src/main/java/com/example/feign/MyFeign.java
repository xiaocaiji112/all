package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "provider",contextId = "1")
public interface MyFeign {
    @GetMapping("/provider/test")
     String test();
    @GetMapping("/provider2/test")
     String test2();
}
