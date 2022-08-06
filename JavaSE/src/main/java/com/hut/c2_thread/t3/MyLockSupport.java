package com.hut.c2_thread.t3;

import java.util.concurrent.locks.LockSupport;

/**
 * t2part -> tiunpark -> t1park -> t2unpark
 */
public class MyLockSupport {

    private static char[] chars1 = "1234567".toCharArray();
    private static char[] chars2 = "ABCDEFG".toCharArray();
    private static Thread t1;
    private static Thread t2;

    /**
     * 交替打印
     */
    public void alternatePrinting() {
        t1 = new Thread(() -> {
            try {
                Thread.sleep(2000); // 就算我先休息两秒也先执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (char c : chars1) {
                System.out.println(c);
                LockSupport.unpark(t2); // 唤醒t2线程
                LockSupport.park(); // 释放锁阻塞住自己等待被唤醒
            }
        });

        t2 = new Thread(() -> {
            for (char c : chars2) {
                LockSupport.park(); // 释放锁阻塞住自己等待被唤醒 保证第一个线程先执行
                System.out.println(c);
                LockSupport.unpark(t1); // 唤醒t1线程
            }
        });

        t1.start();
        t2.start();
    }

}
