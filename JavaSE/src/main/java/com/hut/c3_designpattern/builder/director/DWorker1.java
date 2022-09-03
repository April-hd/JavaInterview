package com.hut.c3_designpattern.builder.director;

import com.hut.c3_designpattern.builder.entities.Product;

/**
 * 具体的工人，具体的实现
 */
public class DWorker1 extends Builder{

    private final Product product = new Product();

    @Override
    public void buildA() throws Exception {
        product.setA("A");
    }

    @Override
    public void buildB() throws Exception {
        product.setB("B");
    }

    @Override
    public void buildC() throws Exception {
        product.setC("C");
    }

    @Override
    public Product buildProduct() throws Exception {
        return product;
    }

}
