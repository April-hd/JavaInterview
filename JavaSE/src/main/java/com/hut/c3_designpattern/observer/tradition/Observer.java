package com.hut.c3_designpattern.observer.tradition;

/**
 * 观察者的抽象类
 */
public abstract class Observer {

    // 保存下被观察的类对象
    protected Subject subject;

    // 抽象的变化方法
    public abstract void update();

}
