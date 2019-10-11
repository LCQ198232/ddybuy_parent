package com.ddybuy.ddybuy_common_service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration //开启
@MapperScan("com.ddybuy.mapper")//扫描mapper   com.ddybuy.mapper
public class DdybuyCommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdybuyCommonServiceApplication.class, args);
    }

}
