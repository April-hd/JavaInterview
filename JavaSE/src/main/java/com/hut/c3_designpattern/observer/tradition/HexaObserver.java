package com.hut.c3_designpattern.observer.tradition;

/**
 * 观察者的实现类---十六进制
 */
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("观察者HexaObserver检测到状态变化，做出反应将状态转为十六进制打印：" + Integer.toHexString(this.subject.getState()));
    }

}
