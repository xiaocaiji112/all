package com.sshop;



import com.sshop.security.config.domain.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("sshop-system")
public interface RemoteUserService {
     @GetMapping(value = "system/user/getuserbyname",headers = {"token=66"})
     public LoginUser getUserByName(String name);
}
