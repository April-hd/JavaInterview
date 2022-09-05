package com.hut.c3_designpattern.observer.tradition;

/**
 * 观察者的实现类---二进制
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        // 观察者BinaryObserver保存被观察的对象subject
        this.subject = subject;
        // 被观察的对象subject保存观察者BinaryObserver，便于subject的状态发生变化后做通知
        this.subject.attach(this);
    }

    /**
     * 当被观察的对象状态发生变化时，将状态转为二进制打印出来
     */
    @Override
    public void update() {
        System.out.println("观察者BinaryObserver检测到状态变化，做出反应将状态转为二进制打印：" + Integer.toBinaryString(this.subject.getState()));
    }

}
