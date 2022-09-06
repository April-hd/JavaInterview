package com.hut.c3_designpattern.chain;

import java.lang.annotation.*;

/**
 * 自定义排序注解
 * order越小越先执行
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Inherited
public @interface Order {

    int value(); // 排序大小

}
