package com.hut.c2_thread.t1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现Callable接口，使任务执行完可以得到返回值
 */
public class ImplementCallable implements Callable {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Object call() throws Exception {
        return "这是实现Callable接口的返回值" + atomicInteger.incrementAndGet();
    }

}
