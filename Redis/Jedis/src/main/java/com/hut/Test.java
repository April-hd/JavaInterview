package com.hut;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hut.entities.User;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 简单测试是否可用
 * 遍历所有的key
 */
public class Test {

    public static void main(String[] args) {

        JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder()
                .password("123456")
                .database(0)
                .build();

        Jedis jedis = new Jedis("192.168.30.128", 6379, jedisClientConfig);

        String pong = jedis.ping();
        System.out.println(pong);

        // 查看所有key
        Set<String> keys = jedis.keys("*");
        // 遍历所有key
        keys.stream().forEach(key -> {
            String type = jedis.type(key);
            switch (type) {
                case "string":
                    System.out.println(type + ":");
                    System.out.println("key: " + key + " value: " + jedis.get(key));
                    break;
                case "list":
                    List<String> list = jedis.lrange(key, 0, -1);
                    System.out.println(type + ":");
                    list.stream().forEach(o -> System.out.println("key: " + key + " value: " + o));
                    break;
                case "set":
                    Set<String> smembers = jedis.smembers(key);
                    System.out.println(type + ":");
                    smembers.stream().forEach(m -> System.out.println("key: " + key + " value: " + m));
                    break;
                case "hash":
                    Map<String, String> map = jedis.hgetAll(key);
                    System.out.println(type + ":");
                    map.keySet().stream().forEach(field -> {
                        System.out.println("key: " + key + " field: " + field + " value: " + map.get(field));
                    });
                    User user = JSONObject.parseObject(JSON.toJSONString(map), User.class);
                    System.out.println(user);
                    break;
                case "zset":
                    List<String> zrange = jedis.zrange(key, 0, -1); // 从小到大排序
                    System.out.println(type + ":");
                    zrange.stream().forEach(z -> System.out.println("key: " + key + " value: " + z));
                    break;
                default:
                    throw new RuntimeException("无法解析：" + type);
            }
        });

    }

}
