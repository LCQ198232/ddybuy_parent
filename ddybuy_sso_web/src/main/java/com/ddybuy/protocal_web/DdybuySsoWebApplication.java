package com.ddybuy.protocal_web;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DdybuySsoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdybuySsoWebApplication.class, args);
    }

}
