package com.hut.c2_thread.t2;

import java.util.Random;

public class Test {

    /**
     * synchronized锁
    */
    public static void testSynchronized() {
        ProductService productService = new ProductService();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                productService.buyProduct01();
            }).start();
        }
    }

    /**
     * ReentrantLock锁使用CAS+AQS的方式
     */
    public static void testReetrantLock() {
        ProductService productService = new ProductService();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                productService.buyProduct02();
            }).start();
        }
    }

    /**
     * AtomicInteger使用CAS修改数据，保证每次只有一个线程修改成功
     */
    public static void testCount() throws InterruptedException {
        CountService countService = new CountService();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                countService.count01(); // 多线程会少加
            }).start();
        }
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                countService.count02(); // 多线程不会少加
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(countService.getI());
        System.out.println(countService.getAtomicInteger().get());
    }

    /**
     * ThreadLocal的set进去的变量都存在各自Thread的
     */
    public static void testThreadLocal() {
        UserService userService = new UserService();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                userService.saveUser(String.valueOf(new Random().nextInt(100)));
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testSynchronized();
        testReetrantLock();
        testCount();
        testThreadLocal();
    }

}
