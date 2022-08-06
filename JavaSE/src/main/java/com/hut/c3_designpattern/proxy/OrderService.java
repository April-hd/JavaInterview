package com.hut.c3_designpattern.proxy;

/**
 * 订单服务
 */
public class OrderService implements OrderInterface {

    /**
     * 添加订单 -> 客户支付 -> 删减库存 -> 发送下游通知
     */
    public void addOrder() {

        System.out.println("添加订单成功，等待客户支付");
        System.out.println("客户支付成功");
        System.out.println("删减产品库存");
        System.out.println("发送下游通知");
//        throw new RuntimeException("发送下游通知失败");

    }

    public void deleteOrder() {
        System.out.println("客户取消订单");
    }

}
