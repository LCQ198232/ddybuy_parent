package com.ddybuy.ddybuy_manager_web;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //开启dubbo
public class DdybuyManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdybuyManagerWebApplication.class, args);
    }

}
