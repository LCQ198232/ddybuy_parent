package com.ddybuy.ddybuy_common_service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddybuy.entity.TbUser;
import com.ddybuy.entity.TbUserExample;
import com.ddybuy.mapper.TbUserMapper;
import com.ddybuy.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service(interfaceClass = TbUserService.class)
@Component
public class TbUserServiceImpl implements TbUserService {

    @Autowired(required = false)
    private TbUserMapper tbUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 实现登录  并存储用户信息到redis
     * @param username  用户名
     * @param password   密码
     * @return  token
     */
    @Override
    public String login(String username,String password) {
        TbUserExample tbUserExample=new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();

        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);

        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        if (list.size()==1){
            //生成token,使用uuid生成
            String token = UUID.randomUUID().toString();
            //将用户信息存入redis
            ValueOperations <String,Object> operations= redisTemplate.opsForValue();
            operations.set("session:"+token,list.get(0),20,TimeUnit.MINUTES);
            return token;
        }else{
            return null;
        }
    }

    /**
     * 获取用户信息
     * @param token  令牌用于登录状态的标识
     * @return  用户信息
     */
    @Override
    public String getUserName(String token) {
        if (redisTemplate.hasKey("session:"+token)){
            //缓存存在说明已经登录成功
            ValueOperations <String,Object> operations= redisTemplate.opsForValue();
            TbUser user = (TbUser)operations.get("session:" + token);
            return user.getUsername();
        }else {
            return null;
        }

    }
}
