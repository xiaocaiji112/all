package com.study.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("study-system")
public interface RemoteUserService {
    @GetMapping("user/get")
    String get(@RequestHeader(value = "inner",defaultValue = "inner") String inner);
}
