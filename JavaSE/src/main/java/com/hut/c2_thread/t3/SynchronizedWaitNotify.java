package com.hut.c2_thread.t3;

/**
 * 先打印A再打印B连续十次
 */
public class SynchronizedWaitNotify {

    private static Object o = new Object();
    private static char[] chars1 = "1234567".toCharArray();
    private static char[] chars2 = "ABCDEFG".toCharArray();

    /**
     * 交替打印
     */
    public void alternatePrinting() {
        new Thread(() -> {
            synchronized (o) {
                for (char c : chars1) {
                    System.out.println(c);
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
                Thread.sleep(100); //确保第一个线程先抢到锁
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
