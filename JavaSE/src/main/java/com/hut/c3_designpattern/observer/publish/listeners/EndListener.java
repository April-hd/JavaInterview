package com.hut.c3_designpattern.observer.publish.listeners;

import com.hut.c3_designpattern.observer.publish.events.EndEvent;

/**
 * 结束事件监听器
 */
public class EndListener implements Listener<EndEvent> {

    // 监听到结束事件，执行之后的逻辑
    @Override
    public void execute() throws Exception {
        System.out.println("逻辑执行完毕！");
    }

}
