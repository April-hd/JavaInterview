package com.hut.c3_designpattern.observer.publish.listeners;

import com.hut.c3_designpattern.observer.publish.events.StartEvent;

/**
 * 开始事件监听器
 */
public class StartListener implements Listener<StartEvent> {

    @Override
    public void execute() throws Exception {
        System.out.println("逻辑开始执行！");
    }

}
