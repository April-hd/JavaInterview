package com.hut.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis连接池
 * 可以解决连接超时问题
 */
public class JedisPoolUtil {

    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {

    }

    /**
     * 获取jedis连接池单例
     * @return
     */
    public static JedisPool getJedisPoolInstance() {
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(200);
                    jedisPoolConfig.setMinIdle(32);
                    jedisPoolConfig.setMaxWaitMillis(100*1000);
                    jedisPoolConfig.setBlockWhenExhausted(true);
                    jedisPoolConfig.setTestOnBorrow(true); // ping pong
                    jedisPool = new JedisPool(jedisPoolConfig, "192.168.30.128", 6379, 60000, "123456");
                }
            }
        }
        return jedisPool;
    }

    /**
     * 释放jedis资源
     * @param jedisPool
     * @param jedis
     */
    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (null != jedisPool)
            jedisPool.returnResource(jedis);
    }

}
