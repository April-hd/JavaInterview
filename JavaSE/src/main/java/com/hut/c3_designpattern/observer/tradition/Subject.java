package com.hut.c3_designpattern.observer.tradition;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察的类
 */
public class Subject {

    // 状态
    private Integer state;

    // 观察者列表
    private List<Observer> observerList;

    // 初始化观察者列表
    {
        observerList = new ArrayList<>();
    }

    public Integer getState() {
        return state;
    }

    /**
     * 当状态发生变化时，通知所有的观察者做出反应
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
        // 通知观察者
        this.notifyAllObservers();
    }

    /**
     * 让Subject保存观察者，好去做通知
     * @param observer
     */
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 通知观察者
     */
    private void notifyAllObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

}
