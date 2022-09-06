package com.hut.c3_designpattern.chain;

/**
 * 校验数据接口
 * @param <T>
 */
public interface CheckHandler<T> {

    /**
     * 校验方法
     * @param data 校验数据对象
     * @throws Exception
     */
    public void check(T data) throws Exception;

}
