package com.ddybuy.ddybuy_manager_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //调用远程服务
    @Reference(interfaceClass = HelloService.class)
    private HelloService helloService;

    @RequestMapping("/getHello")
    @ResponseBody
    public String getHello(){
        String hello = helloService.getHello();
        return hello;
    }
}
