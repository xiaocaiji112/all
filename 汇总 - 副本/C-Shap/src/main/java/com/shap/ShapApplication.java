package com.shap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shap.dao")
public class ShapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShapApplication.class,args);
    }
}
