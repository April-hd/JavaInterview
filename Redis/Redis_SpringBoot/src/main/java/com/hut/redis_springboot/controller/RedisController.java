package com.hut.redis_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * RedisTemplate的简单操作
     */
    @RequestMapping("test")
    public String testRedis() {
        redisTemplate.opsForValue().set("name", "daihui");
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }

}
