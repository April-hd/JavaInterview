package com.hut.c2_thread.t3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 许可证
 * 控制最大并发数
 */
public class MySemaphore {

    /**
     * 最大并发数为20，里面的逻辑如果有涉及到共享数据的话，也需要加锁进行同步，这里不加锁也可以用原子类，但如果是操作对象或者做减法大于的话还是加锁
     */
    private static Semaphore semaphore = new Semaphore(1000); // 许可证
    private static int i = 0;
    private static ReentrantLock reentrantLock = new ReentrantLock(); // CAS+AQS

    public void executeTask() throws InterruptedException {
        semaphore.acquire(); // 获取一个许可证，最多可以用20个线程进入下面的代码，其他的线程在此被阻塞
        reentrantLock.lock(); // 这20个线程需要抢锁，因为下方是共享资源，不加锁这20个线程同时操作i会出现数据紊乱的问题
        try {
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            semaphore.release(); // 释放获得的许可证
        }
    }

    public int getI() {
        return i;
    }

}
