package hut.c2_thread.t3;

public class Test {

    public static void testSynchronizedWaitNotify() {
        SynchronizedWaitNotify synchronizedWaitNotify = new SynchronizedWaitNotify();
        synchronizedWaitNotify.alternatePrinting(); // 交替打印
    }

    public static void testMyLockSupport() {
        MyLockSupport myLockSupport = new MyLockSupport();
        myLockSupport.alternatePrinting();
    }

    public static void testMyCountDownLatch() {
        MyCountDownLantch myCountDownLantch = new MyCountDownLantch();
        myCountDownLantch.alternatePrinting();
    }

    public static void testMyCyclicBarrier() {
        MyCyclicBarrier myCyclicBarrier = new MyCyclicBarrier();
        myCyclicBarrier.alternatePrinting();
    }

    public static void testMySemaphore() throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    mySemaphore.executeTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(mySemaphore.getI());
    }

    public static void main(String[] args) throws InterruptedException {
        testSynchronizedWaitNotify();
        testMyLockSupport();
        testMyCountDownLatch();
        testMyCyclicBarrier();
        testMySemaphore();
    }

}
