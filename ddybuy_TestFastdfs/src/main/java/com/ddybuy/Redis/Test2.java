package com.ddybuy.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


//测试连接池
public class Test2 {

    public static void main(String[] args) throws InterruptedException {

         //创建连接池
          //连接池的配置对象
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(30);  //设置最大连接数
        //连接池对象
        JedisPool jedisPool=new JedisPool(config,"localhost",6379);

        //创建jedis
        Jedis jedis=jedisPool.getResource();

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
