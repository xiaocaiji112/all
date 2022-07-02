package com.czh.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    private Integer userId;

    private String userName;

    private String password;

    private String os;

    private String ip;

    private String address;

    private String browser;
}
