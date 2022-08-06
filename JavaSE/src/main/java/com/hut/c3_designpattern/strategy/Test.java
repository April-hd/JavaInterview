package com.hut.c3_designpattern.strategy;

public class Test {

    /**
     * 普通if-else实现
     */
    public static void testDiscount() {
        OrderService orderService = new OrderService();
        System.out.println(orderService.discount("vip", 100d));
        System.out.println(orderService.discount("supervip", 100d));
        System.out.println(orderService.discount("", 100d));
    }

    /**
     * 基于策略拆分实现
     */
    public static void testDiscountByStrategy() throws Exception {
        OrderService orderService = new OrderService();
        System.out.println(orderService.discountByStrategy("vip", 100d));
        System.out.println(orderService.discountByStrategy("supervip", 100d));
        System.out.println(orderService.discountByStrategy("general", 100d));
    }

    public static void main(String[] args) throws Exception {

        testDiscount();

        testDiscountByStrategy();

    }

}
