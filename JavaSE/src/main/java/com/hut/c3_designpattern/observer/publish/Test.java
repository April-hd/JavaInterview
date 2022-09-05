package com.hut.c3_designpattern.observer.publish;

import com.hut.c3_designpattern.observer.publish.context.MyApplicationContext;
import com.hut.c3_designpattern.observer.publish.events.EndEvent;
import com.hut.c3_designpattern.observer.publish.events.InitEndEvent;
import com.hut.c3_designpattern.observer.publish.events.StartEvent;
import com.hut.c3_designpattern.observer.publish.listeners.Listener;
import org.reflections.Reflections;

import java.util.Set;

public class Test {

    public static void main(String[] args) throws Exception {

        // 创建应用上下文
        MyApplicationContext context = new MyApplicationContext();
        // 注册所有监听器
        registerListeners(context);

        // 逻辑开始
        System.out.println("开始...");
        // 发布开始事件
        context.publish(new StartEvent());
        // 一堆操作...
        // 逻辑结束
        System.out.println("结束...");
        // 发布结束事件
        context.publish(new EndEvent());

    }

    /**
     * 注册所有监听器
     * @param context
     * @throws Exception
     */
    private static void registerListeners(MyApplicationContext context) throws Exception {
        Reflections reflections = new Reflections();
        Set<Class<? extends Listener>> subTypesOf = reflections.getSubTypesOf(Listener.class);
        subTypesOf.stream().forEach(listener -> {
            try {
                context.attach(listener.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 发布监听器执行完毕事件
        context.publish(new InitEndEvent());
    }

}
