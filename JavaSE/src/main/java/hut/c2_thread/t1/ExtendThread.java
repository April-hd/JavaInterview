package hut.c2_thread.t1;

/**
 * java是单继承的，所以继承不利于扩展
 */
public class ExtendThread extends Thread{

    @Override
    public void run() {
        System.out.println("这是继承Thread");
    }

}
