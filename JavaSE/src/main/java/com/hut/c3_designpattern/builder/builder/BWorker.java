package com.hut.c3_designpattern.builder.builder;

import com.hut.c3_designpattern.builder.entities.Product;

/**
 * buildA,buildB,buildC，采用链式编程
 */
public class BWorker extends Builder {

    private final Product product = new Product();

    @Override
    public Builder buildA(String A) throws Exception {
        product.setA(A);
        return this;
    }

    @Override
    public Builder buildB(String B) throws Exception {
        product.setB(B);
        return this;
    }

    @Override
    public Builder buildC(String C) throws Exception {
        product.setC(C);
        return this;
    }

    @Override
    public Product buildProduct() throws Exception {
        return product;
    }

}
