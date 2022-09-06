package com.hut.c3_designpattern.chain;

import lombok.EqualsAndHashCode;

/**
 * 校验用户年龄处理器
 */
@Order(100)
@EqualsAndHashCode
public class CheckAgeHandler implements CheckHandler<User> {

    @Override
    public void check(User user) throws Exception {
        // 用户年满18已成年，校验通过
        if (user.getAge() > 18) {
            System.out.println(user.getName() + "年龄校验通过...");
        } else {
            System.out.println(user.getName() + "年龄校验不通过...");
        }
    }

}
