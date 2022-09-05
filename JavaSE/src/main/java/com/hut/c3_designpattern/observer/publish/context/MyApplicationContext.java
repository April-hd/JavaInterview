package com.hut.c3_designpattern.observer.publish.context;

import com.hut.c3_designpattern.observer.publish.events.Event;
import com.hut.c3_designpattern.observer.publish.listeners.Listener;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplicationContext implements ApplicationContext {

    // 所有的监听器
    private List<Listener> listenerList = new ArrayList<>();

    // 发布事件并通知所有监听该事件的监听者
    @Override
    public void publish(Event event) throws Exception {
        // 找到当前event的监听者，并通知执行execute方法
        listenerList.stream().forEach(listener -> {
            Type[] genericInterfaces = listener.getClass().getGenericInterfaces(); // 获取当前监听器带有的泛型的接口
            Arrays.stream(genericInterfaces).forEach(genericInterface -> {
                ParameterizedType type = (ParameterizedType) genericInterface; // 将接口的泛型参数转换为ParameterizedType
                Type actualTypeArgument = type.getActualTypeArguments()[0]; // 获取第一个泛型参数（可能有多个泛型参数）
                if (actualTypeArgument.getTypeName().equals(event.getClass().getTypeName())) { // 判断监听的接口泛型类型与发布的事件类型是否一致
                    // 一致说明就是要找的监听器
                    try {
                        listener.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    @Override
    public void attach(Listener listener) throws Exception {
        listenerList.add(listener);
    }

}
