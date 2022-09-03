package com.hut.c3_designpattern.builder.director;

import com.hut.c3_designpattern.builder.entities.Product;

/**
 * 定义工人具有的功能（就是工人会的技能，有了这种技能的工人（工人有很多种）才能构建这种产品）
 */
public abstract class Builder {

    public abstract void buildA() throws Exception; // 构建A模块

    public abstract void buildB() throws Exception; // 构建B模块

    public abstract void buildC() throws Exception; // 构建C模块

    public abstract Product buildProduct() throws Exception; // 构建完上面的模块得到最终的产品

}
