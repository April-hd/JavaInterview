package com.hut.c3_designpattern.observer.publish.listeners;

import com.hut.c3_designpattern.observer.publish.events.InitEndEvent;

public class InitEndListener implements Listener<InitEndEvent> {

    @Override
    public void execute() throws Exception {
        System.out.println("注册监听器初始化完毕！");
    }

}
