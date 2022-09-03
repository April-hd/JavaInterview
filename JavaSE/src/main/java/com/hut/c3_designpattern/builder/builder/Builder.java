package com.hut.c3_designpattern.builder.builder;

import com.hut.c3_designpattern.builder.entities.Product;

public abstract class Builder {

    public abstract Builder buildA(String A) throws Exception; // 构建A模块

    public abstract Builder buildB(String B) throws Exception; // 构建B模块

    public abstract Builder buildC(String C) throws Exception; // 构建C模块

    public abstract Product buildProduct() throws Exception; // 构建完上面的模块得到最终的产品

}
