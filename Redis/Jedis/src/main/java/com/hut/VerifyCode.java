package com.hut;

import com.hut.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * redis模拟验证码2分钟过期场景
 */
public class VerifyCode {

    private static final String VERIFYPREFIXX = "Verify-";
    private static final String CODESUFFIX = "-code";
    private static final String COUNTSUFFIX = "-count";

    /**
     * 用户获取验证码
     */
    public static void main(String[] args) {

        String phone = "13627332952";

        // 获取验证码
        Map res = getCode(phone);
        String verifyCode = (String) res.get("verifyCode");
        if (null == verifyCode) {
            System.out.println(res.get("msg"));
        } else {
            // 获取成功，登录校验验证码
            String login = login(phone, verifyCode);
            System.out.println(login);
        }

    }

    /**
     * 用户获取验证码的主入口
     * @return
     */
    public static Map getCode(String phone) {
        HashMap<String, Object> resultMap = new HashMap<>();
        // 1、获取redis连接
        Jedis jedis = JedisUtil.getJedis();
        // 2、判断曾经是否发送过验证码
        String verifyCode = jedis.get(VERIFYPREFIXX + phone + CODESUFFIX);
        if (verifyCode != null) {
            // 不为空代表着上一次发送的验证码还在有效范围内
            resultMap.put("code", 200); // 成功标志码
            resultMap.put("msg", "验证码还在有效范围内！"); // 返回信息
            resultMap.put("verifyCode", verifyCode); // 返回验证码
            return resultMap;
        }
        // 为空代表验证码已失效或者未曾发送过
        // 判断该手机号是否已达到今日发送上限
        String count = jedis.get(VERIFYPREFIXX + phone + COUNTSUFFIX);
        if (count != null && Integer.parseInt(count) > 2) {
            // 已达3次发送上限
            resultMap.put("code", 200); // 成功标志码
            resultMap.put("msg", "已达上限！"); // 返回信息
            resultMap.put("verifyCode", null); // 返回验证码
            return resultMap;
        } else {
            // 生成验证码，发送给客户用于验证
            verifyCode = getVerifyCode();
            // 更新缓存
            jedis.setex(VERIFYPREFIXX + phone + CODESUFFIX, 120, verifyCode); // 验证码120秒的缓存时间
            if (count != null) {
                // 已经有计数直接+1
                jedis.incr(VERIFYPREFIXX + phone + COUNTSUFFIX);
            } else {
                // 走到这里代表今天还没有发送过，所以直接赋予1次
                jedis.set(VERIFYPREFIXX + phone + COUNTSUFFIX, "1");
                // TODO
                // 后续还要每天清理一遍所有的计数
            }
            resultMap.put("code", 200); // 成功标志码
            resultMap.put("msg", "获取成功"); // 返回信息
            resultMap.put("verifyCode", verifyCode); // 返回验证码
        }
        return resultMap;
    }

    /**
     * 生成6位验证码
     */
    public static String getVerifyCode() {
        Random random = new Random();
        String verifyCode = "";
        for (int i = 0; i < 6; i++) {
            verifyCode += random.nextInt(10);
        }
        return verifyCode;
    }

    /**
     * 用户登录，校验验证码
     */
    public static String login(String phone, String verifyCode) {
        String msg = "登录失败";
        Jedis jedis = JedisUtil.getJedis();
        String verifyCodeCache = jedis.get(VERIFYPREFIXX + phone + CODESUFFIX);
        if (verifyCodeCache != null && verifyCodeCache.equals(verifyCode)) {
            // 如果刚刚获取的验证码与缓存里验证码一致，则校验成功
            return "登陆成功";
        }
        return msg;
    }

}
