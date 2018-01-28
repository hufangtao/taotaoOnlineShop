package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJedisSpring {

    @Test
    public void testJedisClientPool() throws Exception{

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:/Spring/ApplicationContext-redis.xml");

        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);

        jedisClient.set("jedisClient","mytest");
        String s = jedisClient.get("jedisClient");
        System.out.println(s);
    }

}
