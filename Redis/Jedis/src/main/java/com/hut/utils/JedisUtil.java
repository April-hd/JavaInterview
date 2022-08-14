package com.hut.utils;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

public class JedisUtil {

    private static String HOST = "192.168.30.128";
    private static Integer PORT = 6379;
    private static String PASSWORD = "123456";
    private static Integer DATABASE = 0;

    public static Jedis getJedis() {
        JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder()
                .password(PASSWORD)
                .database(DATABASE)
                .build();

        Jedis jedis = new Jedis(HOST, PORT, jedisClientConfig);
        return jedis;
    }

}
