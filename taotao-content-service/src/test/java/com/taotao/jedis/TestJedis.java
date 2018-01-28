package com.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class TestJedis {

    @Test
    public void testJedis() throws Exception{
        Jedis jedis=new Jedis("192.168.10.10",6379);

        jedis.set("jedis-key","1234");
        String result = jedis.get("jedis-key");
        System.out.println(result);
        jedis.close();
    }

    @Test
    public void testJedisPool() throws Exception{

        JedisPool jedisPool=new JedisPool("192.168.10.10",6379);
        Jedis jedis=jedisPool.getResource();

        String result = jedis.get("jedis-key");

        System.out.println(result);
        jedis.close();
        jedisPool.close();

    }

    @Test
    public void testJedisCluster() throws Exception{
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.10.10",7001));
        nodes.add(new HostAndPort("192.168.10.10",7002));
        nodes.add(new HostAndPort("192.168.10.10",7003));
        nodes.add(new HostAndPort("192.168.10.10",7004));
        nodes.add(new HostAndPort("192.168.10.10",7005));
        nodes.add(new HostAndPort("192.168.10.10",7006));
        JedisCluster jedisCluster=new JedisCluster(nodes);
        jedisCluster.set("cluster-test","hello jedis cluster");
        String s = jedisCluster.get("cluster-test");
        System.out.println(s);
        jedisCluster.close();
    }

}
