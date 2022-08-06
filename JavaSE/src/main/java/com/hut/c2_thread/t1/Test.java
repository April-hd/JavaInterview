package com.hut.c2_thread.t1;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    /**
     * 测试继承Thread
     */
    public static void testExtendThread() {
        ExtendThread t = new ExtendThread();
        t.start();
    }

    /**
     * 测试实现Runnable接口
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testImplementRunnable() throws ExecutionException, InterruptedException {
        ImplementRunnable implementRunnable = new ImplementRunnable();
        Thread t = new Thread(implementRunnable);
        t.start();
    }

    /**
     * 测试实现Callable接口
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testImplementCallable() throws ExecutionException, InterruptedException {
        ImplementCallable implementCallable = new ImplementCallable();
        FutureTask futureTask = new FutureTask<>(implementCallable);
        futureTask.run();
        Object o = futureTask.get();
        System.out.println(o);
    }

    /**
     * 测试线程池执行任务
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testThreadPool() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = MyThreadPool.getThreadPoolExecutor();
        ArrayList<Future> arrayList = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            threadPoolExecutor.execute(new ImplementRunnable()); // 立即执行，不会阻塞主线程
//        }
        /**
         * 谨记：千万不能再for循环里去future.get()
         */
        for (int i = 0; i < 20; i++) {
            Future future = threadPoolExecutor.submit(new ImplementCallable());
//            arrayList.add((String) future.get() + i); // future.get() 会阻塞当前主线程的运行，直到收到结果，
            arrayList.add(future); // 把所有任务丢到线程池里，再把所有future拿到外面去，再去获取返回值，现在才是多线程执行，如果像上面一行那样做，丢一个任务就去get返回值，那就要等该任务执行完了拿到返回值了，才去丢下一个任务，这不就变成了串行执行任务了吗，大大影响了效率
        }
        for (Future future : arrayList) {
            System.out.println(future.get()); // 阻塞拿所有任务得返回值
        }
        threadPoolExecutor.shutdown(); // 会等待所有任务执行完才关闭线程池
//        threadPoolExecutor.shutdownNow(); // 会尝试中断正在执行的任务，不处理剩余的任务
    }

    /**
     * 测试 future.get() 的阻塞情况
     */
    public static void testThreadPool01() {
        ThreadPoolExecutor threadPoolExecutor = MyThreadPool.getThreadPoolExecutor();
        Future<String> future1 = threadPoolExecutor.submit(() -> {
            System.out.println("任务一开始执行");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务一执行结束");
            return "任务一====>true";
        });
//        System.out.println(future1.get());
        Future<String> future2 = threadPoolExecutor.submit(() -> {
            System.out.println("任务二开始执行");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务二执行结束");
            return "任务二====>true";
        });
//        System.out.println(future2.get());
        Future<String> future3 = threadPoolExecutor.submit(() -> {
            System.out.println("任务三开始执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务三执行结束");
            return "任务三====>true";
        });
//        System.out.println(future3.get());
        try {
            // 尽管任务一执行的最久，但是我的三个任务是并行执行得，下面同一获得返回值，比我丢一个任务拿一次返回值快的多，因为future1.get()会阻塞主线程
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testExtendThread();
//
//        testImplementRunnable();
//
//        testImplementCallable();
//
//        testThreadPool();

        testThreadPool01();

    }

}
