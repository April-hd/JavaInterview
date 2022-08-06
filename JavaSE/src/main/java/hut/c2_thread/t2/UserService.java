package hut.c2_thread.t2;

/**
 * 线程安全问题
 * ThreadLocal可以让类的成员变量的进行线程之间隔离
 * 用户服务
 */
public class UserService {

    /**
     * ThreadLocal的set的值都在各自的线程里ThreadLocal.ThreadLocalMap的map里
     * map存放的是一个Entry extends WeakReference
     * key是外面的tl,value是设置的值
     * referent = tl, value = 塞进去的值
     * 所以key是弱引用，value是强引用
     * 因此必须手动remove才能释放value释放内存，key是弱引用，被GC一扫就回收（如果没有其他强引用关联的话）不然会产生内存泄露
     *
     * 应用场景：比如数据库连接，一个线程一个，需要从上层方法一直传到下层方法去用，就可以扔到ThreadLocal里面，下层方法直接去取就可以了，而不必一层一层用形参去传送
     */
    private static ThreadLocal usernameTL = new ThreadLocal();

    public void saveUser(String username) {
        usernameTL.set(username);
        A();
    }

    public void A() {
        B();
    }

    public void B() {
        C();
    }

    public void C() {
        System.out.println(usernameTL.get());
        usernameTL.remove();
    }

}
