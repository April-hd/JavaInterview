package com.hut.c3_designpattern.observer.tradition;

public class Test {

    public static void main(String[] args) {
        // 被观察的对象
        Subject subject = new Subject();
        // 观察者---BinaryObserver
        new BinaryObserver(subject);
        // 观察者---OctalObserver
        new OctalObserver(subject);
        // 观察者---HexaObserver
        new HexaObserver(subject);
        // 对象状态发生变化
        subject.setState(15);
    }

}
