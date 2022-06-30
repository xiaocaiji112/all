package com.shop.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("shop-system")
public interface UserService {
    //别忘了路径啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
    @GetMapping("/system/goods/test")
     String a();
}
