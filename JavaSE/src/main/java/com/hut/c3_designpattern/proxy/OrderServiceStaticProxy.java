package com.hut.c3_designpattern.proxy;

/**
 * 静态代理
 * 当类里的方法很多的时候，就需要对每一个方法进行增强
 */
public class OrderServiceStaticProxy {

    private OrderService orderService;

    public OrderServiceStaticProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder() {
        System.out.println("开始事务");
        try {
            orderService.addOrder();
            System.out.println("提交事务");
        } catch (Exception e) {
            System.out.println("事务回滚");
            e.printStackTrace();
        }
    }

}
