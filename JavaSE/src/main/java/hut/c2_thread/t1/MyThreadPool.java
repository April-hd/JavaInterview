package hut.c2_thread.t1;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1、任务过来
 * 2、启用核心线程 -> 核心线程数满了 -> 丢进任务队列列 -> 任务队列满了 -> 启用非核心线程数 -> 非核心线程数满了 -> 拒绝任务策略
 * 3、所有任务执行完，销毁线程池
 */
public class MyThreadPool {

    private static volatile ThreadPoolExecutor threadPoolExecutor = null;
    private static Object o = new Object();

    private MyThreadPool() {

    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (null == threadPoolExecutor) {
            synchronized (o) {
                if (null == threadPoolExecutor) {
                    threadPoolExecutor = new ThreadPoolExecutor(
                            5, // 核心线程数量
                            10, // 最大线程数量
                            500, // 非核心线程存活时间
                            TimeUnit.SECONDS, // 非核心线程存活时间单位
                            new LinkedBlockingQueue<>(100), // 任务队列
                            Executors.defaultThreadFactory(), // 默认线程工厂
                            new ThreadPoolExecutor.AbortPolicy() // 当核心线程数量满了，任务队列满了，非核心线程数量也满了，再有任务过来的拒绝策略
                    );
                }
            }
        }
        return threadPoolExecutor;
    }

}
