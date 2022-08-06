package com.hut.c2_thread.t3;

import java.util.concurrent.CountDownLatch;

/**
 * 做减法，只有countDownLatch变为0，被await的线程才能执行
 */
public class MyCountDownLantch {

    private static Object o = new Object();
    private static char[] chars1 = "1234567".toCharArray();
    private static char[] chars2 = "ABCDEFG".toCharArray();

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 交替打印
     */
    public void alternatePrinting() {
        new Thread(() -> {
            synchronized (o) {
                try {
                    Thread.sleep(2000); // 就算我先休息两秒也先执行，因为第二个线程要等我放下门栓才能执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (char c : chars1) {
                    System.out.println(c);
                    countDownLatch.countDown(); // -1 放下一个门栓变为0
                    try {
                        o.notify(); // 随机唤醒等待里面的一个线程
                        o.wait(); // 释放锁进入等待队列里
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 因为最终会有一个线程在等待队列等待被唤醒
            }
        }).start();

        new Thread(() -> {
            try {
                countDownLatch.await(); // 确保第一个线程先执行，等待第一个线程放下门栓变为0我才能走下面的方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c : chars2) {
                    System.out.println(c);
                    try {
                        o.notify(); // 随机唤醒等待里面的一个线程
                        o.wait(); // 释放锁进入等待队列
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 因为最终会有一个线程在等待队列等待被唤醒
            }
        }).start();
    }

}
