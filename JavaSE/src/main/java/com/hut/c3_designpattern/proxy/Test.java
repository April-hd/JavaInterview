package com.hut.c3_designpattern.proxy;

public class Test {

    /**
     * 没有代理，出错了数据不能回滚
     */
    public static void testAddOrder() {
        OrderService orderService = new OrderService();
        System.out.println(orderService);
        orderService.addOrder();
    }

    /**
     * 静态代理开启事务
     */
    public static void testAddOrderSimpleProxy() {
        OrderService orderService = new OrderService();
        OrderServiceStaticProxy orderServiceStaticProxyInstance = new OrderServiceStaticProxy(orderService);
        System.out.println(orderServiceStaticProxyInstance);
        orderServiceStaticProxyInstance.addOrder();
    }

    /**
     * JDK动态代理
     */
    public static void testAddOrderJDKProxy() throws Exception {
        OrderService orderService = new OrderService();
        OrderInterface orderServiceJDKProxyInstance = new OrderServiceJDKProxy(orderService).getOrderServiceJDKProxyInstance();
        System.out.println(orderServiceJDKProxyInstance);
        orderServiceJDKProxyInstance.addOrder();
    }

    /**
     * Cglib动态代理
     */
    public static void testAddOrderCglibProxy() {
        OrderService orderService = new OrderService();
        OrderService orderServiceCglibProxyInstance = new OrderServiceCglibProxy(orderService).getOrderServiceCglibProxyInstance();
        System.out.println(orderServiceCglibProxyInstance);
        orderServiceCglibProxyInstance.addOrder();
//        orderServiceCglibProxyInstance.deleteOrder();
    }

    public static void main(String[] args) throws Exception {
        testAddOrder();

        testAddOrderSimpleProxy();

        testAddOrderJDKProxy();

        testAddOrderCglibProxy();

    }

}
