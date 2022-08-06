package com.hut.c3_designpattern.singleton;

/**
 * 懒汉式单例
 * 注意点：
 * 1、懒汉式单例对象不能用final修饰，因为一开始是null，加了final修饰词，便不可改变变量的值
 * 2、多线程情况下会产生线程安全问题 DCL+Volatile
 */
public class Lazy {

    private static volatile Lazy lazy = null;
    private static Object o = new Object(); // 锁对象

    private Lazy() {

    }

    public static Lazy getInstance() {
        if (null == lazy) { // 3、如果对象已经被创建好了，后续的获取过程中不应该再去抢锁判断了，避免不必要的操作
            synchronized (o) {
                if (null == lazy) { // 2、后面没抢到锁的线程判断创建好了就不再去创建对象了
                    lazy = new Lazy(); // 1、第一个抢到锁的线程创建对象，但是由于创建对象的指令可能重排，会导致其他线程拿到一个半初始化对象，所以加volatile禁止指令重拍
                }
            }
        }
        return lazy;
    }

    public void instruction() {
        System.out.println("我是懒汉式单例对象" + this.hashCode());
    }

}
