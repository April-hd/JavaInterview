package hut.c2_thread.t1;

/**
 * 实现Runnable接口
 */
public class ImplementRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("这是实现Runnable接口的线程");
    }

}
