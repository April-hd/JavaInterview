package com.hut.c3_designpattern.builder.director;

import com.hut.c3_designpattern.builder.entities.Product;

/**
 * 具体的工人，另一种实现
 */
public class DWorker2 extends Builder{

    private final Product product = new Product();

    @Override
    public void buildA() throws Exception {
        product.setA("AA");
    }

    @Override
    public void buildB() throws Exception {
        product.setB("BB");
    }

    @Override
    public void buildC() throws Exception {
        product.setC("CC");
    }

    @Override
    public Product buildProduct() throws Exception {
        return product;
    }

}
