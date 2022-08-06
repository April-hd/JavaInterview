package com.hut.c2_thread.t2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全问题
 * 产品服务
 * 多线程抢商品
 */
public class ProductService {

    // 20个产品
    private static int i0 = 20;
    // 20个产品
    private static int i1 = 20;

    private static Object o = new Object(); // synchronized锁，JDK1.6以后有锁升级过程

    private static ReentrantLock lock = new ReentrantLock(); // ReentrantLock锁使用CAS+AQS的方式

    public void buyProduct01() {
        if (i0 > 0) {
            synchronized (o) {
                if (i0 > 0) {
                    System.out.println(Thread.currentThread().getName() + "抢到一个产品一，目前剩余产品：" + --i0);
                } else {
                    System.out.println("商品一抢购完毕，i = " + i0);
                }
            }
        }
    }

    public void buyProduct02() {
        if (i1 > 0) {
            lock.lock();
            try {
                if (i1 > 0) {
                    System.out.println(Thread.currentThread().getName() + "抢到一个产品二，目前剩余产品：" + --i1);
                } else {
                    System.out.println("商品二抢购完毕，i = " + i1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
