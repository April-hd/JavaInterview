package com.hut.c3_designpattern.chain;

import java.util.Comparator;

/**
 * 按order值从小到大排序，越小越先执行
 */
public class OrderComparator implements Comparator<Class<? extends CheckHandler>> {

    @Override
    public int compare(Class<? extends CheckHandler> o1, Class<? extends CheckHandler> o2) {
        Order o1Order = o1.getAnnotation(Order.class);
        Order o2Order = o2.getAnnotation(Order.class);
        if (o1Order != null && o2Order != null) {
            return o1Order.value() - o2Order.value();
        }
        return 0;
    }

}
