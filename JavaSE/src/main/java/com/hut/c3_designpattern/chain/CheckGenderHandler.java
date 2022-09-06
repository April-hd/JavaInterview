package com.hut.c3_designpattern.chain;

import lombok.EqualsAndHashCode;

/**
 * 校验用户性别处理器
 */
@Order(200)
@EqualsAndHashCode
public class CheckGenderHandler implements CheckHandler<User>{

    private Integer zero = 0;
    private Integer one = 1;

    @Override
    public void check(User user) throws Exception {
        if (zero.equals(user.getGender()) || one.equals(user.getGender())) {
            System.out.println(user.getName() + "性别校验通过...");
        } else {
            // 如果用户性别既不是女性也不是男性，校验不通过
            System.out.println(user.getName() + "性别校验不通过...");
        }
    }

}
