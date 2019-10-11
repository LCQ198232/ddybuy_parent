package com.ddybuy.ddybuy_common_service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.ddybuy.service.HelloService;
import org.springframework.stereotype.Component;

@Service(interfaceClass = HelloService.class)
@Component   //创建对象
public class HelloServiceImpl implements HelloService {
    @Override
    public String getHello() {
        return "Hello,哈皮";
    }
}
