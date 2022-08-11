package com.hut.c3_designpattern.factory.abs.a;

import com.hut.c3_designpattern.factory.abs.Trousers;

public class ATrousers implements Trousers {

    @Override
    public void make() throws Exception {
        System.out.println("这是A裤子");
    }

}
