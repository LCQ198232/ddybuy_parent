package com.ddybuy.datailitem_web;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DdybuySearchWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(DdybuySearchWebApplication.class, args);
    }

}
