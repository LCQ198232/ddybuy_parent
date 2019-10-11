package com.ddybuy.service;

public interface TbUserService {
    //用户登录
    public String login(String username,String password);

    //获取用户信息
    public  String getUserName(String token);
}
