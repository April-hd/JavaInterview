package com.hut.c3_designpattern.strategy;

public interface DiscountStrategy {

    public String getType() throws Exception;

    public double discount(double price) throws Exception;

}
