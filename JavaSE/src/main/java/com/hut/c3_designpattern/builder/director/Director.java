package com.hut.c3_designpattern.builder.director;

import com.hut.c3_designpattern.builder.entities.Product;

/**
 * 指挥者类--->指挥工人--->创建产品
 */
public class Director {

    public Product getProduct(Builder builder) throws Exception {
        // 指挥者指挥工人的构建产品的过程（顺序）；A->B->C
        // 指挥者可以任意更改构建顺序
        builder.buildA();
        builder.buildB();
        builder.buildC();
        // 当每一块都构建完成，指挥者通过工人得到最终产品
        Product product = builder.buildProduct();
        return product;
    }

}
