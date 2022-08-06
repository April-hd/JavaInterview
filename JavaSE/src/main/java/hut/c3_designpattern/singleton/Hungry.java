package hut.c3_designpattern.singleton;

/**
 * 饿汉式单例
 * 注意点：
 * 1、饿汉式单例对象加不加final修饰根据情况而定，如果后续需要释放对象，则不用加final，如果不需要释放对象，则可以加可以不加
 */
public class Hungry {

    /**
     * 为了防止其他人拿到这个类对象里的唯一实例对象置空，所以hungry必须用private修饰保证不被改变(也可加上fianl让其他人不能改变这个变量)，提供public的getInstance方法
     * 由于getInstance是类对象才能访问的，所以hungry成员变量也必须是在类对象里面，所以加static
     */
    private final static Hungry hungry = new Hungry();

    /**
     * 构造器private，让其他人不能new对象，只能在本类中创建对象并且是唯一的
     */
    private Hungry() {

    }

    /**
     * 由于其他人不能new对象，所以方法上要加static，变成方法只能是类对象访问，通过类对象调用getInstance方法获得类的唯一实例对象hungry
     * @return
     */
    public static Hungry getInstance() {
        return hungry;
    }

    /**
     * 介绍方法
     */
    public void instruction() {
        System.out.println("我是饿汉式单例对象" + this.hashCode());
    }

}