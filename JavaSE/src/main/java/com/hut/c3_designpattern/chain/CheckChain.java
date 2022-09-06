package com.hut.c3_designpattern.chain;

import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户校验链
 */
public class CheckChain<T> {

    // 用来保存要执行的校验处理器
    private final LinkedList<CheckHandler<T>> handlers = new LinkedList();

    public CheckChain() {
    }

    /**
     * 添加校验处理器
     * @param checkHandler
     */
    public void addCheckHandler(CheckHandler<T> checkHandler) {
        handlers.add(checkHandler);
    }

    public void addCheckHandler(CheckHandler<T>... checkHandlers) {
        Arrays.stream(checkHandlers).sequential().sorted(new Comparator<CheckHandler<T>>() {
            @Override
            public int compare(CheckHandler<T> o1, CheckHandler<T> o2) {
                Order o1Order = o1.getClass().getAnnotation(Order.class);
                Order o2Order = o2.getClass().getAnnotation(Order.class);
                if (o1Order != null && o2Order != null) {
                    return o1Order.value() - o2Order.value();
                }
                return 0;
            }
        }).forEach(checkHandler -> {
            if (!handlers.contains(checkHandler)) {
                handlers.add(checkHandler);
            }
        });
    }

    /**
     * 执行校验链
     * @param t
     * @throws Exception
     */
    public void doCheckChain(T t) throws Exception {
        // 从集合中取出处理器并执行
        while (handlers.size() > 0) {
            CheckHandler<T> checkHandler = handlers.pollFirst();
            checkHandler.check(t);
        }
    }

    /**
     * 注册所有处理器
     */
    public void registerAllCheckHandlers() {
        // 注入所有的处理器
        Reflections reflections = new Reflections();
        Set<Class<? extends CheckHandler>> subTypesOf = reflections.getSubTypesOf(CheckHandler.class);
        subTypesOf.stream().sorted(new OrderComparator()).forEach(aClass -> {
                try {
                    handlers.add(aClass.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }


}
