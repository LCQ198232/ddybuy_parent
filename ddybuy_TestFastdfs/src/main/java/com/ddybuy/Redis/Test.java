package com.ddybuy.Redis;

import redis.clients.jedis.Jedis;


// 测试jedis操作redis
public class Test {

    public static void main(String[] args) throws InterruptedException {
        //创建jedis
        Jedis jedis=new Jedis("localhost",6379);

        //使用jedis 操作redis
        jedis.set("name","zhangsan");
        jedis.expire("name",10);//设置缓存有效时间
        String name = jedis.get("name");
        System.out.println(name);

        Thread.sleep(2000);
        System.out.println(jedis.ttl("name"));//获取有效缓存时间

        //关闭jedis
        jedis.close();
    }
}
