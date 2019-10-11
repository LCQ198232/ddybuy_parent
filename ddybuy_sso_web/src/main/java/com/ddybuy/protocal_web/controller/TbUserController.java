package com.ddybuy.protocal_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddybuy.service.TbUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TbUserController {

    @Reference(interfaceClass = TbUserService.class)
    private TbUserService tbUserService;

    @RequestMapping("/login")
    @CrossOrigin
    public String login(String username, String password, HttpServletResponse response){
        if (username==null&&password==null){
            return "Login";
        }else {
            //掉业务
            String token = tbUserService.login(username, password);

            if (token==null){
                return "Login";
            }else {
                //登陆成功,存储cookie
                Cookie cookie=new Cookie("token",token);
                cookie.setPath("/");  //解决cookie不同子系统中跨域
                cookie.setMaxAge(1200);  //1200秒  20分钟
                response.addCookie(cookie);
                return "redirect:http://localhost:8003/index";
            }
        }

    }

    @RequestMapping("/getUserName")
    @ResponseBody
    @CrossOrigin  //支持跨域访问 跨系统
    public Map<String,String> getUserName(String token){
        //掉业务
        String userName = tbUserService.getUserName(token);
        Map<String,String> map=new HashMap<>();
        map.put("result",userName);
        return map;
    }
}
