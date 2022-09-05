package com.hut.c3_designpattern.observer.tradition;

/**
 * 观察者的实现类---八进制
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("观察者OctalObserver检测到状态变化，做出反应将状态转为八进制打印：" + Integer.toOctalString(this.subject.getState()));
    }

}
