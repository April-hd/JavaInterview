package com.hut.c3_designpattern.builder.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 此注解会帮我们生成此类的静态内部类，静态内部类的build方法会使用此类的全参构造，也就是如果没有指明该类的构造方法，默认生成的是全参构造（default），那就会导致如果需要使用无参构造的时候报错，所以最好直接定义出来无参和全参
//@Accessors(chain = true) // 此注解会更改原生的set方法，变成链式编程写法，具体可以看此类的class文件
public class Product3 {

    private String A;

    private String B;

    private String C;

}
