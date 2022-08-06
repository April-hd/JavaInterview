package com.hut.c3_designpattern.singleton;

public class Test {

    /**
     * 测试饿汉式单例
     */
    public static void testHungryInstrance() {
        Hungry hungry = Hungry.getInstance();
        hungry.instruction();
    }

    /**
     * 测试多线程情况获取懒汉式单例
     */
    public static void testLazyInstance() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Lazy lazy = Lazy.getInstance();
                lazy.instruction();
            }).start();
        }
    }

    public static void main(String[] args) {

        testHungryInstrance();

        testLazyInstance();

    }

}
