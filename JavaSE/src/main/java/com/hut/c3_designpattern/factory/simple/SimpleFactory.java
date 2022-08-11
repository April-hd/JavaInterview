package com.hut.c3_designpattern.factory.simple;

public class SimpleFactory {

    public SimpleProduct getProduct() {
        return new SimpleProduct().setProductName("简单产品");
    }

}
