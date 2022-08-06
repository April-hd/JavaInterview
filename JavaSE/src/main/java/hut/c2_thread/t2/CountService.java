package hut.c2_thread.t2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全问题
 * 计数
 */
public class CountService {

    private static int i = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 多线程情况下会出现线程安全问题，会少加
     */
    public void count01() {
        i++;
    }

    /**
     * 多线程情况下使用CAS保证不会少加
     */
    public void count02() {
        atomicInteger.incrementAndGet();
    }

    public int getI() {
        return i;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

}
