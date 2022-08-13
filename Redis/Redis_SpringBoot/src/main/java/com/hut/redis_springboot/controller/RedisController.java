package com.hut.redis_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis是 单线程 + IO多路复用
 *
 * Redis事务是一个单独的隔离操作：事务中的所有命令都会序列化，按顺序执行
 * 事务在执行过程中，不会被其他客户端发送来的命令请求所打断
 * Redis事务的主要作用就是串联多个命令防止别的命令插队
 * multi开启事务 -> 发送命令进入队列 -> 等命令发完然后exec依次执行队列里的命令结束事务 / 或者discard取消所有发送的命令结束事务
 *
 * Redis的事务和MySQL的事务是有区别的
 * 注意点：
 * 1、假如在multi之后，往队列里加命令的时候，如果错写了一条错误的命令，会导致后续执行exec时，往队列里加的命令全部执行失败
 * 比如：
 * multi
 * set k1 v1
 * set k2 v2
 * set k3
 * exec
 * 全部执行失败
 * 2、如果在multi之后、往队列里加命令的时候，全部是正确的命令，然后exec命令执行队列里所有的命令，如果其中有命令执行失败，不会影响其他命令执行
 * 比如：
 * multi
 * set k1 v1
 * incr k1 (对v1 + 1，虽然命令没问题，但是v1是string类型的，不能加1，后续执行会出错，但不会影响其他命令执行)
 * set k2 v2
 * exec
 * 其中只有incr k1失败
 *
 * watch 可以取监视某个key，当redis客户端，只要有一个改成功了这个key的值，就会让其他客户端事务的全部操作失败，（乐观锁）
 * 每个客户端只会监视一次，当某个客户端对其操作成功后，就需要重新watch
 *
 * 使用watch+redis事务虽然能解决并发安全超卖的问题，但是会出现库存遗留的问题
 * 因为watch+redis事务的这种操作，有些线程会失败，假如商品足够多，但是并发下大部分线程同时抢由于乐观锁出现抢失败了，如果客户没有重新抢的话就可能会有商品遗留的问题
 * lua脚本解决超卖和遗留问题，实质上是利用redis单线程解决并发问题，当一个客户端在操作时，其他客户端都不能操作
 */
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
