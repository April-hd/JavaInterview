package com.hut.c3_designpattern.strategy;

/**
 * vip客户策略
 */
public class VipStrategy implements DiscountStrategy {

    private static final String TYPE = "vip";

    @Override
    public String getType() throws Exception {
        return TYPE;
    }

    @Override
    public double discount(double price) throws Exception {
        return price * 0.9;
    }

}
